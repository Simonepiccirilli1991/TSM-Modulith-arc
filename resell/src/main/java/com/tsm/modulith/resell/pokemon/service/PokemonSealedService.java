package com.tsm.modulith.resell.pokemon.service;


import com.tsm.modulith.resell.onepiece.dto.BaseResponse;
import com.tsm.modulith.resell.onepiece.dto.Vendita;
import com.tsm.modulith.resell.onepiece.dto.request.AddVenditaSealedOpRequest;
import com.tsm.modulith.resell.onepiece.dto.response.AddVenditaSealedOpResponse;
import com.tsm.modulith.resell.onepiece.entity.SealedOp;
import com.tsm.modulith.resell.onepiece.exception.OpException;
import com.tsm.modulith.resell.pokemon.entity.PokemonSealed;
import com.tsm.modulith.resell.pokemon.exception.PokemonException;
import com.tsm.modulith.resell.pokemon.model.request.AddSealedPokemonRequest;
import com.tsm.modulith.resell.pokemon.model.request.AddVenditaSealedPokemonRequest;
import com.tsm.modulith.resell.pokemon.model.response.AddSealedPokemonResponse;
import com.tsm.modulith.resell.pokemon.model.response.AddVenditaSealedPokemonResponse;
import com.tsm.modulith.resell.pokemon.repository.PokemonSealedRepo;
import com.tsm.modulith.resell.pokemon.utils.PokemonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonSealedService {



    private final PokemonSealedRepo pokemonSealedRepo;
    private final PokemonUtils pokemonUtils;

    // addo sealed
    public AddSealedPokemonResponse addSealedPokemon(AddSealedPokemonRequest addSealedPokemonRequest) {
        log.info("AddSealedPokemonService started with raw request: {}", addSealedPokemonRequest);
        // valido request
        addSealedPokemonRequest.validaRequest();

        var idSealed = pokemonUtils.generaIdPokemonSealed();

        var entity = new PokemonSealed();
        entity.setId(idSealed);
        entity.setNomeSealed(addSealedPokemonRequest.nomeSealed());
        entity.setEspansione(addSealedPokemonRequest.espansione());
        entity.setCodiceSealed(addSealedPokemonRequest.codiceSealed());
        entity.setDataAcquisto(addSealedPokemonRequest.dataAcquisto());
        entity.setAcquistataPresso(addSealedPokemonRequest.acquistatoPresso());
        entity.setPrezzoAcquisto(addSealedPokemonRequest.prezzoAcquisto());
        // setto foto se esiste
        if(!ObjectUtils.isEmpty(addSealedPokemonRequest.foto()))
            entity.setFoto(addSealedPokemonRequest.foto());

        // salvo entity
        var sealed = pokemonSealedRepo.save(entity);
        var resp = new AddSealedPokemonResponse("Pokemon sealed salvato con successo",sealed);
        log.info("Pokemon sealed salvato con successo: {}", resp);
        return resp;
    }

    // delete sealed
    public BaseResponse cancellaSealedPokemon(String idSealed){
        log.info("Cancella sealed pokemon service started with id: {}", idSealed);
        //ricerco entity
        var sealedOpt = pokemonSealedRepo.findById(idSealed)
                .orElseThrow(() -> {
                log.error("Cancella sealed pokemon service failed with id: {}", idSealed);
                return new PokemonException("Sealed pokemon non trovato con id: " + idSealed, "POKEMON SEALED NOT FOUND");
                });
        // cancello entity
        pokemonSealedRepo.delete(sealedOpt);
        log.info("Sealed pokemon cancellato con successo con id: {}", idSealed);
        return new BaseResponse("Sealed pokemon cancellato con successo");
    }


    // vendita sealed
    public AddVenditaSealedPokemonResponse addVenditaSealedPokemon(AddVenditaSealedPokemonRequest request){
        log.info("AddVenditaSealed Pokemon Service started with raw request: {}",request);
        // valido request
        request.validaRequest();
        // recupero oggetto sealed
        var sealedPkm = pokemonSealedRepo.findById(request.idAcquisto())
                .orElseThrow(() -> {
                    log.error("Error on AddVenditaSealed Pokemonservice, oggetto sealed non presente con id indicato");
                    return new OpException("Oggetto Pokemon sealed non presente","INTERNAL ERROR");
                });

        // mappo request su vendita
        var vendita = new Vendita();
        vendita.setDataVendita(request.dataVendita());
        vendita.setPiattaformaVendita(request.piattaformaVendita());
        vendita.setPrezzoVendita(request.prezzoVendita());
        // calcolo il netto a valle dei costi accessori se presenti
        if(!ObjectUtils.isEmpty(request.costiAccessori()) && 0.00 != request.costiAccessori())
            vendita.setPrezzoNetto((request.prezzoVendita() - request.costiAccessori()));

        // cambio lo stato sul sealed
        sealedPkm.setStato("Non Disponibile");
        sealedPkm.setStatoAcquisto("Venduto");
        sealedPkm.setVendita(vendita);
        // aggiorno
        var entity = pokemonSealedRepo.save(sealedPkm);
        var resp = new AddVenditaSealedPokemonResponse("Vendita sealed Pokemon aggiunta con successo",entity);
        log.info("AddVenditaSealed Pokemon service ended successfully with resp: {}",resp);
        return resp;
    }


}

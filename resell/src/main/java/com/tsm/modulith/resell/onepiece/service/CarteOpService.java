package com.tsm.modulith.resell.onepiece.service;

import com.tsm.modulith.resell.onepiece.dto.BaseResponse;
import com.tsm.modulith.resell.onepiece.dto.Vendita;
import com.tsm.modulith.resell.onepiece.dto.request.AddCardOpRequest;
import com.tsm.modulith.resell.onepiece.dto.request.AddVenditaCartaOpRequest;
import com.tsm.modulith.resell.onepiece.dto.response.AddCartaOpResponse;
import com.tsm.modulith.resell.onepiece.dto.response.AddVenditaCartaOpResponse;
import com.tsm.modulith.resell.onepiece.entity.CarteOp;
import com.tsm.modulith.resell.onepiece.entity.SealedOp;
import com.tsm.modulith.resell.onepiece.exception.OpException;
import com.tsm.modulith.resell.onepiece.oputils.OpUtils;
import com.tsm.modulith.resell.onepiece.repository.CarteOpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarteOpService {

    private final CarteOpRepo carteOpRepo;
    private final OpUtils opUtils;

    // add carta
    public AddCartaOpResponse addCarta(AddCardOpRequest request) {
        log.info("AddCartaOpService started with raw request: {}", request);

        // valido request
        request.validareRequest();
        // valido in caso sia gradata che siano presenti i campi necessari
        if (request.gradata())
            request.validateGradata();

        // genero id carta
        var idCarta = opUtils.generaIdCarta();
        var entity = new CarteOp();
        entity.setIdCarta(idCarta);
        entity.setNomeCarta(request.nomeCarta());
        entity.setCodiceCarta(request.codiceCarta());
        entity.setEspansione(request.espansione());
        entity.setCondizioniCarta(request.condizioniCarta());
        entity.setAcquistataPresso(request.acquistataPresso());
        entity.setCostoCarta(request.costoCarta());
        entity.setDataAcquisto(request.dataAcquisto());
        entity.setNote(request.note());
        entity.setGradata(request.gradata());
        if (request.gradata()) {
            entity.setEnteGradazione(request.enteGradazione());
            entity.setVotoGradazione(request.votoGradazione());
        }
        // setto parametri di default
        entity.setStato("Disponibile");
        entity.setStatoAcquisto("Acquistata");
        // salvo a db
        var savedEntity = carteOpRepo.save(entity);
        var response = new AddCartaOpResponse("Carta OP aggiunta con successo",savedEntity);
        log.info("Carta OP salvata con id: {}", savedEntity.getIdCarta());
        return response;
    }

    // delete
    public BaseResponse cancellaCartaOp(String idcarta){
        log.info("Cancella carta op service started con id: {}", idcarta);

        //ricerco entity
        var carta = carteOpRepo.findById(idcarta)
                .orElseThrow(() -> {
                    log.error("Cancella carta op service error , carta not found with id: {}", idcarta);
                    return new OpException("Carta non trovata con provided id","INTERNAL ERROR");
                });

        // cancello entity
        carteOpRepo.delete(carta);
        var response = new BaseResponse("Carta op cancellata con successo");
        log.info("Cancella carta op service ended successfully");
        return response;
    }

    // add vendita
    public AddVenditaCartaOpResponse addVenditaCarta(AddVenditaCartaOpRequest request) {
        log.info("AddVenditaCartaOpService started with raw request: {}", request);
        // valido request
        request.validaRequest();
        // recupero acquisto carta
        var cartaOp = carteOpRepo.findById(request.idCarta())
                .orElseThrow(() -> {
                    log.error("Error on AddVenditaCarta service, oggetto carta op non presente con id indicato");
                    return new OpException("Oggetto Op carta non presente","INTERNAL ERROR");
                });

        // monto i dati di venduta
        // mappo request su vendita
        var vendita = new Vendita();
        vendita.setDataVendita(request.dataVendita());
        vendita.setPiattaformaVendita(request.piattaformaVendita());
        vendita.setPrezzoVendita(request.prezzoVendita());
        // calcolo il netto a valle dei costi accessori se presenti
        if(!ObjectUtils.isEmpty(request.costiAccessori()) && 0.00 != request.costiAccessori())
            vendita.setPrezzoNetto((request.prezzoVendita() - request.costiAccessori()));

        // cambio lo stato sul sealed
        cartaOp.setStato("Non Disponibile");
        cartaOp.setStatoAcquisto("Venduto");
        cartaOp.setVendita(vendita);
        // salvo a db
        var venditaOp = carteOpRepo.save(cartaOp);
        // setto resp
        var response = new AddVenditaCartaOpResponse("Vendita aggiunta con successo",venditaOp);
        log.info("AddVenditaCartaOpService ended successfully");
        return response;
    }

    // get carta by id
    public CarteOp getIdCartaOP(String idCarta){
        log.info("GetIdCarta OP Service started with id: {}",idCarta);

        var cartaOp = carteOpRepo.findById(idCarta)
                .orElseThrow(() -> {
                    log.error("Error on GetIdCartaOp service, oggetto sealed non presente con id indicato");
                    return new OpException("Oggetto Op carta non presente","INTERNAL ERROR");
                });

        log.info("GetIdCarta OP Service ended successfully with carta op: {}",cartaOp);
        return cartaOp;
    }
    // get sealed by nome
    public List<CarteOp> getCartaOPByNome(String nomeSealed){
        log.info("GetSealedOpBy nome service started with nome: {}",nomeSealed);

        var cartaOp = carteOpRepo.findByNomeCarta(nomeSealed);

        log.info("GetCartaOpByNome service ended successfully with resp: {}",cartaOp);
        return cartaOp;
    }

    // get sealed by fascia prezzo
    public List<CarteOp> getCartaOpByFasciaPrezzo(Double fasciaPrezzoStart,Double fasciaPrezzoEnd){
        log.info("GetCartaOpByFasciPrezzo service started with fascia prezzo: {} , {}",fasciaPrezzoStart,fasciaPrezzoEnd);

        var carteOp = carteOpRepo.findByCostoAcquistoBetween(fasciaPrezzoStart,fasciaPrezzoEnd);

        log.info("GetCartaOpByFasciaPrezzo service ended successfully with resp: {}",carteOp);
        return carteOp;
    }

    // get sealed op by data
    public List<CarteOp> getCartaOpByFasciaDataAcquisto(LocalDate dataStart, LocalDate dataEnd){
        log.info("GetCartaOpByFasciaDataAcquisto service started with data acquisto: {} , {}",dataStart,dataEnd);

        var cartaOps = carteOpRepo.findByDataAcquistoBetween(dataStart,dataEnd);

        log.info("GetCartaOpByFasciaDataAcquisto service ended successfully with resp: {}",cartaOps);
        return cartaOps;
    }

    // get sealed op by stato
    public List<CarteOp> getCartaOpByStato(String stato) {
        log.info("GetCartaOpByStato service started with stato: {}", stato);

        var carteOps = carteOpRepo.findByStato(stato);

        log.info("GetCartaOpByStato service ended successfully with resp: {}", carteOps);
        return carteOps;
    }

    // get sealed op by sgtato acquisto
    public List<CarteOp> getCartaOpByStatoAcquisto(String statoAcquisto) {
        log.info("GetCarteOpByStatoAcquisto service started with stato acquisto: {}", statoAcquisto);

        var carteOps = carteOpRepo.findByStatoAcquisto(statoAcquisto);

        log.info("GetCarteOpByStatoAcquisto service ended successfully with resp: {}", carteOps);
        return carteOps;
    }
}

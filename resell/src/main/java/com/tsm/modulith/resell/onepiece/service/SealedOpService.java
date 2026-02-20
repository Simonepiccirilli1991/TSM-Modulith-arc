package com.tsm.modulith.resell.onepiece.service;


import com.tsm.modulith.resell.onepiece.dto.BaseResponse;
import com.tsm.modulith.resell.onepiece.dto.Vendita;
import com.tsm.modulith.resell.onepiece.dto.request.AddSealedOpRequest;
import com.tsm.modulith.resell.onepiece.dto.request.AddVenditaSealedOpRequest;
import com.tsm.modulith.resell.onepiece.dto.response.AddSealedOpResponse;
import com.tsm.modulith.resell.onepiece.dto.response.AddVenditaSealedOpResponse;
import com.tsm.modulith.resell.onepiece.entity.SealedOp;
import com.tsm.modulith.resell.onepiece.exception.OpException;
import com.tsm.modulith.resell.onepiece.oputils.OpUtils;
import com.tsm.modulith.resell.onepiece.repository.SealedOpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SealedOpService {

    private final SealedOpRepo sealedOpRepo;
    private final OpUtils opUtils;

    // add sealed
    public AddSealedOpResponse addSealedOP(AddSealedOpRequest request){
        log.info("AddSealedOp Service started with raw request: {}",request);
        // valido request
        request.validaRequest();
        // validata la request mappo su dto
        var sealedOp = new SealedOp();
        var idSealed = opUtils.generaIdSealed();
        sealedOp.setIdSealed(idSealed);
        sealedOp.setNomeSealed(request.nomeSealed());
        sealedOp.setCodiceSealed(request.codiceSealed());
        sealedOp.setEspansione(request.espansione());
        sealedOp.setAcquistatoPresso(request.acquistatoPresso());
        sealedOp.setCostoAcquisto(request.costoAcquisto());
        sealedOp.setDataAcquisto(request.dataAcquisto());
        sealedOp.setStato("Disponibile");
        sealedOp.setStatoAcquisto("Acquistato");
        if(!ObjectUtils.isEmpty(request.note()))
            sealedOp.setNote(request.note());
        if(!ObjectUtils.isEmpty(request.foto()))
            sealedOp.setFoto(request.foto());
        //salvo a db
        var entity = sealedOpRepo.save(sealedOp);
        // monto response
        var resp = new AddSealedOpResponse("Salvato con successo sealed op",entity);
        log.info("AddSealedOp Service ended successfully with response : {}", resp);
        // ritorno la resp
        return resp;
    }

    // delete
    public BaseResponse deleteSealedById(String idSealed){
        log.info("DeleteSealedOp service started for id: {}",idSealed);

        // recuper il sealed
        var sealedOp = sealedOpRepo.findById(idSealed)
                .orElseThrow(() -> {
                    log.error("DeleteSealedOp service failed for id: {}",idSealed);
                    return new OpException("Oggetto sealed non trovato con id: "+idSealed,"INTERNAL ERROR");
                });

        // deleto
        sealedOpRepo.delete(sealedOp);
        var resp = new BaseResponse("Oggetti sealed eliminato con successo");
        log.info("DeleteSealedOp service ended successfully with response : {}", resp);
        return resp;
    }

    // vendita sealed
    public AddVenditaSealedOpResponse addVenditaSealedOP(AddVenditaSealedOpRequest request){
        log.info("AddVenditaSealed OP Service started with raw request: {}",request);
        // valido request
        request.validaRequest();
        // recupero oggetto sealed
        var sealedOp = sealedOpRepo.findById(request.idSealed())
                .orElseThrow(() -> {
                    log.error("Error on AddVenditaSealed service, oggetto sealed non presente con id indicato");
                    return new OpException("Oggetto Op sealed non presente","INTERNAL ERROR");
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
        sealedOp.setStato("Non Disponibile");
        sealedOp.setStatoAcquisto("Venduto");
        sealedOp.setVendita(vendita);
        // aggiorno
        var entity = sealedOpRepo.save(sealedOp);
        var resp = new AddVenditaSealedOpResponse("Vendita sealed op aggiunta con successo",entity);
        log.info("AddVenditaSealed op service ended successfully with resp: {}",resp);
        return resp;
    }

    // get sealed by id
    public SealedOp getIdSealedOP(String idSealed){
        log.info("GetIdSealed OP Service started with id: {}",idSealed);

        var sealedOp = sealedOpRepo.findById(idSealed)
                .orElseThrow(() -> {
                    log.error("Error on GetIdSealed service, oggetto sealed non presente con id indicato");
                    return new OpException("Oggetto Op sealed non presente","INTERNAL ERROR");
                });

        log.info("GetIdSealed OP Service ended successfully with sealed op: {}",sealedOp);
        return sealedOp;
    }
    // get sealed by nome
    public List<SealedOp> getSealedOPByNome(String nomeSealed){
        log.info("GetSealedOpBy nome service started with nome: {}",nomeSealed);

        var sealedOps = sealedOpRepo.findByNomeSealed(nomeSealed);

        log.info("GetSealedOpByNome service ended successfully with resp: {}",sealedOps);
        return sealedOps;
    }

    // get sealed by fascia prezzo
    public List<SealedOp> getSealedOpByFasciaPrezzo(Double fasciaPrezzoStart,Double fasciaPrezzoEnd){
        log.info("GetSelaedOpByFasciPrezzo service started with fascia prezzo: {} , {}",fasciaPrezzoStart,fasciaPrezzoEnd);

        var sealedOps = sealedOpRepo.findByCostoAcquistoBetween(fasciaPrezzoStart,fasciaPrezzoEnd);

        log.info("GetSealedOpByFasciaPrezzo service ended successfully with resp: {}",sealedOps);
        return sealedOps;
    }

    // get sealed op by data
    public List<SealedOp> getSealedOpByFasciaDataAcquisto(LocalDate dataStart, LocalDate dataEnd){
        log.info("GetSealedOpByFasciaDataAcquisto service started with data acquisto: {} , {}",dataStart,dataEnd);

        var sealedOps = sealedOpRepo.findByDataAcquistoBetween(dataStart,dataEnd);

        log.info("GetSealedOpByFasciaDataAcquisto service ended successfully with resp: {}",sealedOps);
        return sealedOps;
    }

    // get sealed op by stato
    public List<SealedOp> getSealedOpByStato(String stato) {
        log.info("GetSealedOpByStato service started with stato: {}", stato);

        var sealedOps = sealedOpRepo.findByStato(stato);

        log.info("GetSealedOpByStato service ended successfully with resp: {}", sealedOps);
        return sealedOps;
    }

    // get sealed op by sgtato acquisto
    public List<SealedOp> getSealedOpByStatoAcquisto(String statoAcquisto) {
        log.info("GetSealedOpByStatoAcquisto service started with stato acquisto: {}", statoAcquisto);

        var sealedOps = sealedOpRepo.findByStatoAcquisto(statoAcquisto);

        log.info("GetSealedOpByStatoAcquisto service ended successfully with resp: {}", sealedOps);
        return sealedOps;
    }
}

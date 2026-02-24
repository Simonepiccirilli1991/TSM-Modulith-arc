package com.tsm.modulith.resell.onepiece.dto.request;

import com.tsm.modulith.resell.onepiece.exception.OpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Slf4j
public record AddCardOpRequest(
        String nomeCarta,
        String codiceCarta,
        String espansione,
        String condizioniCarta,
        String acquistataPresso,
        Double costoCarta,
        LocalDate dataAcquisto,
        String note,
        boolean gradata,
        String enteGradazione,
        String votoGradazione) {


    public void validareRequest(){

        if(ObjectUtils.isEmpty(nomeCarta) || ObjectUtils.isEmpty(codiceCarta) || ObjectUtils.isEmpty(espansione)
        || ObjectUtils.isEmpty(condizioniCarta) || ObjectUtils.isEmpty(acquistataPresso) || ObjectUtils.isEmpty(costoCarta)
        || ObjectUtils.isEmpty(dataAcquisto)){
            log.error("Errore in addCartaOpRequest: campi obbligatori mancanti");
            throw new OpException("Parametri mancanti per aggiungere una carta. Assicurati di fornire nomeCarta, codiceCarta, espansione, condizioniCarta, acquistataPresso, costoCarta e dataAcquisto.","INVALID REQUEST");
        }
    }


    public void validateGradata(){
        if(Boolean.TRUE.equals(gradata)){
            if(ObjectUtils.isEmpty(enteGradazione) || ObjectUtils.isEmpty(votoGradazione)){
                log.error("Errore in addCartaOpRequest: campi obbligatori mancanti per carta gradata");
                throw new IllegalArgumentException("Per una carta gradata, ente di gradazione e voto di gradazione sono obbligatori.");
            }
        }
    }
}

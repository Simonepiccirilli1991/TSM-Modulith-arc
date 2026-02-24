package com.tsm.modulith.resell.pokemon.model.request;

import com.tsm.modulith.resell.onepiece.exception.OpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Slf4j
public record AddSealedPokemonRequest(

        String nomeSealed,
        String espansione,
        String acquistataPresso,
        Double prezzoAcquisto,
        String codiceSealed,
        LocalDate dataAcquisto,
        String acquistatoPresso,
        byte[] foto
) {

    public void validaRequest(){

        if(ObjectUtils.isEmpty(nomeSealed) || ObjectUtils.isEmpty(espansione) || ObjectUtils.isEmpty(codiceSealed)
                || ObjectUtils.isEmpty(dataAcquisto) || ObjectUtils.isEmpty(prezzoAcquisto) || ObjectUtils.isEmpty(acquistatoPresso)){
            log.info("Errore in validazione request per Aggiunta Sealed Pokemon");
            throw new OpException("Paramentro mancante in request aggiunta sealed Pokemon","INVALID REQUEST");
        }
    }
}

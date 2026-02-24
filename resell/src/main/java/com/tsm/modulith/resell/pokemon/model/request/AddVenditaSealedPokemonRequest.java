package com.tsm.modulith.resell.pokemon.model.request;

import com.tsm.modulith.resell.onepiece.exception.OpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Slf4j
public record AddVenditaSealedPokemonRequest(
        String idAcquisto,
        LocalDate dataVendita,
        Double prezzoVendita,
        Double costiAccessori,
        String piattaformaVendita
) {

    public void validaRequest(){

        if(ObjectUtils.isEmpty(dataVendita) || ObjectUtils.isEmpty(prezzoVendita)
                || ObjectUtils.isEmpty(idAcquisto) || ObjectUtils.isEmpty(piattaformaVendita)){
            log.error("Error on AddVenditaSealedPokemon, parametri mancanti in request","INVALID REQUEST");
            throw new OpException("Paramentri mancanti in request per vendita pokemon sealed","INVALID REQUEST");
        }
    }
}

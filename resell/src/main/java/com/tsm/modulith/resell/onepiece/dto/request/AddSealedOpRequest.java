package com.tsm.modulith.resell.onepiece.dto.request;

import com.tsm.modulith.resell.onepiece.exception.OpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Slf4j
public record AddSealedOpRequest(

        String nomeSealed,
        String espansione,
        String codiceSealed,
        LocalDate dataAcquisto,
        String acquistatoPresso,
        Double costoAcquisto,
        String note

) {

        private void validaRequest(){

            if(ObjectUtils.isEmpty(nomeSealed) || ObjectUtils.isEmpty(espansione) || ObjectUtils.isEmpty(codiceSealed)
            || ObjectUtils.isEmpty(dataAcquisto) || ObjectUtils.isEmpty(costoAcquisto)){
                log.info("Errore in validazione request per Aggiunta Sealed OP");
                throw new OpException("Paramentro mancante in request aggiunta sealed op","INVALID REQUEST");
            }
        }

}

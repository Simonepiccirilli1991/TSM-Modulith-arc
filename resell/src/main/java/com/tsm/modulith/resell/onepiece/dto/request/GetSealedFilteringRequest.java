package com.tsm.modulith.resell.onepiece.dto.request;

import java.time.LocalDate;

public record GetSealedFilteringRequest(

        String idSealed,
        String nome,
        String stato,
        String statoAcquisto,
        Double fasciaPrezzoStart,
        Double fasciaPrezzoEnd,
        LocalDate fasciaDataStart,
        LocalDate fasciaDataEnd

) {

}

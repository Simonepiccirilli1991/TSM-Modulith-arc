package com.tsm.modulith.resell.onepiece.entity;


import com.tsm.modulith.resell.onepiece.dto.Vendita;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@Document("sealed-op")
public class SealedOp {

    @MongoId
    private String idSealed;

    private String nomeSealed;
    private String espansione;
    private String codiceSealed;
    private LocalDate dataAcquisto;
    private String acquistatoPresso;
    private Double costoAcquisto;
    private String note;
    private Vendita vendita;

}

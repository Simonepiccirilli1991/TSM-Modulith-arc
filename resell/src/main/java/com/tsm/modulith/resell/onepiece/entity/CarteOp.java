package com.tsm.modulith.resell.onepiece.entity;

import com.tsm.modulith.resell.onepiece.dto.Vendita;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@Document("carte-op")
public class CarteOp {


    @MongoId
    private String idCarta;

    private String nomeCarta;
    private String codiceCarta;
    private String espansione;
    private String condizioniCarta;
    private String acquistataPresso;
    private Double costoCarta;
    private LocalDate dataAcquisto;
    private String note;
    private Vendita vendita;
}

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
    //sezione stato
    private String stato; // disponibile & non disponibile
    private String statoAcquisto; // acquistata & venduta
    // sezione per gradata
    private boolean gradata;
    private String enteGradazione;
    private String votoGradazione;
    // sezione venduta
    private Vendita vendita;
}

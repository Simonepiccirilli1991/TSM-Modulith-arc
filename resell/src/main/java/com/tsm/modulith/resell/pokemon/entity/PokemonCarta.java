package com.tsm.modulith.resell.pokemon.entity;


import com.tsm.modulith.resell.onepiece.dto.Vendita;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@Document("pokemon_carta")
public class PokemonCarta {

    @MongoId
    private String id;

    private String nomeCarta;
    private String codiceCarta;
    private String espansione;
    private LocalDate dataAcquisto;
    private Double prezzoAcquisto;
    private String acquistataPresso;
    private byte[] foto;

    // sezione gradata
    private boolean gradata;
    private String enteGradazione;
    private String votoGradazione;
    // sezione stati
    private String stato;
    private String statoAcquisto;
    // vendita
    private Vendita vendita;

}

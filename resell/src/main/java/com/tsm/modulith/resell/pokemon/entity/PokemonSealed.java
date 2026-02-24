package com.tsm.modulith.resell.pokemon.entity;

import com.tsm.modulith.resell.onepiece.dto.Vendita;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@Document("pokemon_sealed")
public class PokemonSealed {

    @MongoId
    private String id;

    private String nomeSealed;
    private String espansione;
    private String acquistataPresso;
    private Double prezzoAcquisto;
    private String codiceSealed;
    private LocalDate dataAcquisto;
    private byte[] foto;
    // sezione stato
    private String stato;
    private String statoAcquisto;
    // vendita
    private Vendita vendita;
}

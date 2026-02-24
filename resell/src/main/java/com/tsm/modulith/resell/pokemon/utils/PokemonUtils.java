package com.tsm.modulith.resell.pokemon.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class PokemonUtils {


    public String generaIdPokemonSealed(){
        var idSealed = "PKM-SEAL-"+ UUID.randomUUID();
        return idSealed;
    }

    public String generaPokemonCarta(){
        var idCarta = "PKM-CARD-"+UUID.randomUUID();
        return idCarta;
    }
}

package com.tsm.modulith.resell.pokemon.exception;

import lombok.Data;

@Data
public class PokemonException extends RuntimeException{

    private String messaggio;
    private String codiceErrore;


    public PokemonException(String messaggio, String codiceErrore) {
        this.messaggio = messaggio;
        this.codiceErrore = codiceErrore;
    }
}

package com.tsm.modulith.resell.onepiece.exception;

import lombok.Data;

@Data
public class OpException extends RuntimeException{

    private String messaggio;
    private String codice;

    public OpException(String messaggio, String codice) {
        this.messaggio = messaggio;
        this.codice = codice;
    }
}

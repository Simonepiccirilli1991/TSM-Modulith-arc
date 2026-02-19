package com.tsm.modulith.resell.onepiece.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Vendita {


    private LocalDate dataVendita;
    private Double prezzoVendita;
    private Double costiAccessori;
    private Double prezzoNetto;
    private String piattaformaVendita;
}

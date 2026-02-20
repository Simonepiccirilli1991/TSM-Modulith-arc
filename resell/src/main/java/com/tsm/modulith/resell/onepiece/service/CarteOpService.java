package com.tsm.modulith.resell.onepiece.service;

import com.tsm.modulith.resell.onepiece.repository.CarteOpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarteOpService {

    private final CarteOpRepo carteOpRepo;


    //TODO: implementare i metodi per la gestione delle carte, gemella al sealed tranne che per la sezione gradata
}

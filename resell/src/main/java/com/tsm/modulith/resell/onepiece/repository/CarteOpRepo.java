package com.tsm.modulith.resell.onepiece.repository;

import com.tsm.modulith.resell.onepiece.entity.CarteOp;
import com.tsm.modulith.resell.onepiece.entity.SealedOp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarteOpRepo extends MongoRepository<CarteOp, String> {

    List<CarteOp> findByNomeCarta(String nome);

    // Query custom: costoAcquisto compreso tra min e max (inclusi)
    @Query("{ 'costoAcquisto': { $gte: ?0, $lte: ?1 } }")
    List<CarteOp> findByCostoAcquistoBetweenCustom(Double min, Double max);

    // Query derivata: restituisce tutti i SealedOp con costoAcquisto tra min e max (inclusi)
    List<CarteOp> findByCostoAcquistoBetween(Double min, Double max);

    // query derivata: range (inclusi)
    List<CarteOp> findByDataAcquistoBetween(LocalDate start, LocalDate end);

    // query custom: range con JSON MongoDB
    @Query("{ 'dataAcquisto': { $gte: ?0, $lte: ?1 } }")
    List<CarteOp> findByDataAcquistoBetweenCustom(LocalDate start, LocalDate end);

    List<CarteOp> findByStato(String stato);

    List<CarteOp> findByStatoAcquisto(String statoAcquisto);
}

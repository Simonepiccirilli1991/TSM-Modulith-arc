package com.tsm.modulith.resell.onepiece.repository;

import com.tsm.modulith.resell.onepiece.entity.SealedOp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SealedOpRepo extends MongoRepository<SealedOp, String> {

    List<SealedOp> findByNomeSealed(String nome);

    // Query custom: costoAcquisto compreso tra min e max (inclusi)
    @Query("{ 'costoAcquisto': { $gte: ?0, $lte: ?1 } }")
    List<SealedOp> findByCostoAcquistoBetweenCustom(Double min, Double max);

    // Query derivata: restituisce tutti i SealedOp con costoAcquisto tra min e max (inclusi)
    List<SealedOp> findByCostoAcquistoBetween(Double min, Double max);

    // query derivata: range (inclusi)
    List<SealedOp> findByDataAcquistoBetween(LocalDate start, LocalDate end);

    // query custom: range con JSON MongoDB
    @Query("{ 'dataAcquisto': { $gte: ?0, $lte: ?1 } }")
    List<SealedOp> findByDataAcquistoBetweenCustom(LocalDate start, LocalDate end);

    List<SealedOp> findByStato(String stato);

    List<SealedOp> findByStatoAcquisto(String statoAcquisto);
}

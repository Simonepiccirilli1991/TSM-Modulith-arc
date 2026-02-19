package com.tsm.modulith.resell.onepiece.repository;

import com.tsm.modulith.resell.onepiece.entity.CarteOp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteOpRepo extends MongoRepository<CarteOp, String> {
}

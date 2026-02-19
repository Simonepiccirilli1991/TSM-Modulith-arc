package com.tsm.modulith.resell.onepiece.repository;

import com.tsm.modulith.resell.onepiece.entity.SealedOp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SealedOpRepo extends MongoRepository<SealedOp, String> {
}

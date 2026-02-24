package com.tsm.modulith.resell.pokemon.repository;

import com.tsm.modulith.resell.pokemon.entity.PokemonSealed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonSealedRepo extends MongoRepository<PokemonSealed, String> {
}

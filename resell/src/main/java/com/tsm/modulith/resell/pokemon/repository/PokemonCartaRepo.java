package com.tsm.modulith.resell.pokemon.repository;

import com.tsm.modulith.resell.pokemon.entity.PokemonCarta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCartaRepo extends MongoRepository<PokemonCarta, String> {
}

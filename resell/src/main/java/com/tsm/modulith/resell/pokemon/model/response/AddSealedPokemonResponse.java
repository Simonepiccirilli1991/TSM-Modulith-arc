package com.tsm.modulith.resell.pokemon.model.response;

import com.tsm.modulith.resell.pokemon.entity.PokemonCarta;
import com.tsm.modulith.resell.pokemon.entity.PokemonSealed;

public record AddSealedPokemonResponse(

        String messaggio,
        PokemonSealed pokemonSealed
) {
}

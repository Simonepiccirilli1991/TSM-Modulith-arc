package com.tsm.modulith.resell.pokemon.model.response;

import com.tsm.modulith.resell.pokemon.entity.PokemonSealed;

public record AddVenditaSealedPokemonResponse(
        String messaggio,
        PokemonSealed pokemonSealed
) {
}

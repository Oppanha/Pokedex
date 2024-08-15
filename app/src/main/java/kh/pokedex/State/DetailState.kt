package kh.pokedex.State

import kh.pokedex.Data.Remote.Response.Pokemon

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val data: Pokemon? = null,
    val error: String? = null
)
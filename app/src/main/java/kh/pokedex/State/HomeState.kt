package kh.pokedex.State

import kh.pokedex.Data.Model.ListItemData.ResultPokemon

data class HomeState(
    val data: List<ResultPokemon>? = emptyList(),
)
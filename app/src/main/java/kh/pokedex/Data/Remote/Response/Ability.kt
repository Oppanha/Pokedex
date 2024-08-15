package kh.pokedex.Data.Remote.Response

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)
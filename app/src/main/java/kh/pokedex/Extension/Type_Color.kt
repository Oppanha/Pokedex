package kh.pokedex.Extension

import androidx.compose.ui.graphics.Color
import kh.pokedex.ui.theme.Bug
import kh.pokedex.ui.theme.Dark
import kh.pokedex.ui.theme.Dragon
import kh.pokedex.ui.theme.Electric
import kh.pokedex.ui.theme.Fairy
import kh.pokedex.ui.theme.Fighting
import kh.pokedex.ui.theme.Fire
import kh.pokedex.ui.theme.Flying
import kh.pokedex.ui.theme.Ghost
import kh.pokedex.ui.theme.Grass
import kh.pokedex.ui.theme.Ground
import kh.pokedex.ui.theme.Ice
import kh.pokedex.ui.theme.Normal
import kh.pokedex.ui.theme.Poison
import kh.pokedex.ui.theme.Psychic
import kh.pokedex.ui.theme.Rock
import kh.pokedex.ui.theme.Steel
import kh.pokedex.ui.theme.Water


// Type Color
fun String.getColorByType(): Color {
    return when (this) {
        "bug" -> Bug
        "dark" -> Dark
        "dragon" -> Dragon
        "electric" -> Electric
        "fairy" -> Fairy
        "fighting" -> Fighting
        "fire" -> Fire
        "flying" -> Flying
        "ghost" -> Ghost
        "normal" -> Normal
        "grass" -> Grass
        "ground" -> Ground
        "ice" -> Ice
        "poison" -> Poison
        "psychic" -> Psychic
        "rock" -> Rock
        "steel" -> Steel
        "water" -> Water
        else -> Color.DarkGray
    }
}
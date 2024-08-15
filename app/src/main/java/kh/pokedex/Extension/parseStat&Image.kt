package kh.pokedex.Extension

import androidx.compose.ui.graphics.Color
import kh.pokedex.Data.Remote.Response.Stat
import kh.pokedex.ui.theme.Orage

//Stat Color
fun parseStatToColor(stat: Stat): Color {
    return when (stat.stat.name) {
        "hp" -> Color.Red
        "attack" -> Orage
        "defense" -> Color.Yellow
        "special-attack" -> Color.Cyan
        "special-defense" -> Color.Green
        "speed" -> Color.Blue
        else -> Color.Gray
    }
}

fun parseStateToAbbr(stat: Stat): String {
    return when (stat.stat.name) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SpA"
        "special-defense" -> "SpD"
        "speed" -> "SPD"
        else -> stat.stat.name
    }
}

// Link Image to Api
fun String.getImageFromName(): String {
    return "https://img.pokemondb.net/artwork/${this}.jpg"
//    return  "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/metapod.png"

//    return "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/${this}.png"
}




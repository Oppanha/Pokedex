package kh.pokedex.Component

import androidx.compose.runtime.Composable

@Composable
fun String.CapitalizeLetter(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}
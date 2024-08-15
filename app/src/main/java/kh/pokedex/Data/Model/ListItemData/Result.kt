package kh.pokedex.Data.Model.ListItemData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultPokemon(
    val name: String,
    val url: String
) : Parcelable

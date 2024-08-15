package kh.pokedex.Data.Model.ListItemData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon_Model(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ResultPokemon>
): Parcelable
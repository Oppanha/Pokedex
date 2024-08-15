package kh.pokedex.ViewHolder

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.pokedex.Data.Model.ListItemData.ResultPokemon
import kh.pokedex.Data.Remote.Response.Pokemon
import kh.pokedex.Repository.Repository
import kh.pokedex.State.PokemonDetailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonDetailState())
    val state: StateFlow<PokemonDetailState> = _state.asStateFlow()

    val dataState = mutableStateOf(PokemonDetailState())

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPokemonInfo(name)
                _state.update {
                    state.value.copy(
                        data = response.body()
                    )
                }
                dataState.value = PokemonDetailState(data = response.body())

            } catch (e: Exception) {
                _state.value = PokemonDetailState(error = e.message)
            }
        }
    }
}
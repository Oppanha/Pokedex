package kh.pokedex.ViewHolder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import kh.pokedex.Data.Model.ListItemData.ResultPokemon
import kh.pokedex.Data.Remote.Response.Pokemon
import kh.pokedex.Repository.Repository
import kh.pokedex.Source.HomeSource
import kh.pokedex.State.HomeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ViewModelMain(private val repository: Repository) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state

    val pokemon: Flow<PagingData<ResultPokemon>> = Pager(PagingConfig(pageSize = 20)) {
        HomeSource(repository)
    }.flow.cachedIn(viewModelScope)

    //Search Functon
    fun SearchGrid(
        search: String,
        pokemonListItem: LazyPagingItems<ResultPokemon>
    ): MutableStateFlow<PagingData<ResultPokemon>> {
        val filterDataSearch = pokemonListItem.itemSnapshotList.items.filter { item ->
            item.name.contains(
                search,
                ignoreCase = false
            )
        }
        return MutableStateFlow(PagingData.from(filterDataSearch));
    }

}
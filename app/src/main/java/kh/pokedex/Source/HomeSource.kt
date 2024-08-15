package kh.pokedex.Source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kh.pokedex.Data.Model.ListItemData.ResultPokemon
import kh.pokedex.Repository.Repository

class HomeSource(private val repository: Repository) : PagingSource<Int, ResultPokemon>() {
    override fun getRefreshKey(state: PagingState<Int, ResultPokemon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultPokemon> {
        return try {
            val nextPage = params.key ?: 1
            val dataList = repository.getPokemon(offset = nextPage * 10, limit = 20)
            LoadResult.Page(
                data = dataList.body()?.results.orEmpty(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

}
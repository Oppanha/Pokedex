package kh.pokedex.Repository

import dagger.hilt.android.scopes.ActivityScoped
import kh.pokedex.Data.ClientApi.PokemonApi
import kh.pokedex.Data.Model.ListItemData.Pokemon_Model
import kh.pokedex.Data.Remote.Response.Pokemon
import javax.inject.Inject

class Repository(

) {
    suspend fun getPokemon(offset: Int, limit: Int): retrofit2.Response<Pokemon_Model> {
        return RetrofitInstance.api.getPokemonApi(offset, limit)
    }

    suspend fun getPokemonInfo(name: String): retrofit2.Response<Pokemon> {
        return RetrofitInstance.api.getPokemonInfoApi(name)
    }
}
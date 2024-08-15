package kh.pokedex.Data.ClientApi

import kh.pokedex.Data.Model.ListItemData.Pokemon_Model
import kh.pokedex.Data.Remote.Response.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.sql.ResultSet

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonApi(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int

    ): Response<Pokemon_Model>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfoApi(
        @Path("name") name: String
    ): Response<Pokemon>
}
package kh.pokedex.Repository

import kh.pokedex.Data.ClientApi.PokemonApi
import kh.pokedex.Util.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply{
            this.level = HttpLoggingInterceptor.Level.BODY
        }).build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api: PokemonApi by lazy {
        retrofit.create(PokemonApi::class.java)
    }}
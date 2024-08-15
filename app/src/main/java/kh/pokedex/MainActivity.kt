package kh.pokedex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kh.pokedex.Data.Model.ListItemData.ResultPokemon
import kh.pokedex.Data.Remote.Response.Pokemon
import kh.pokedex.Repository.Repository
import kh.pokedex.Screen.DetailsScreen
import kh.pokedex.Screen.HomeScreen
import kh.pokedex.ViewHolder.DetailViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(navController = navController)
                }
                composable("details") {
                    val result =
                        navController.previousBackStackEntry?.savedStateHandle?.get<ResultPokemon>("DetailsScreen")
                    if (result != null) {
                        val viewModel = DetailViewModel(repository = Repository())
                        viewModel.fetchPokemonDetail(result.name)
                        DetailsScreen(
                            navController = navController,
                            viewModel = viewModel,
                        )
                    }
                }
            }
        }
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package kh.pokedex.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import kh.pokedex.Screen.Widget.GridItem
import kh.pokedex.Component.LoadingAnimation
import kh.pokedex.Component.ShimerHome
import kh.pokedex.Screen.Widget.Search
import kh.pokedex.Extension.Mypref.getColorByDayOfWeek
import kh.pokedex.Extension.Mypref.getCurrentDayOfWeek
import kh.pokedex.Screen.Widget.SortList
import kh.pokedex.R
import kh.pokedex.Repository.Repository
import kh.pokedex.ViewHolder.MainViewModelFactory
import kh.pokedex.ViewHolder.ViewModelMain

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    val TextState = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val searchText = TextState.value
    val viewModel: ViewModelMain = viewModel(factory = MainViewModelFactory(Repository()))
    var pokemonListItem = viewModel.pokemon.collectAsLazyPagingItems()

    if (TextState.value.text.isNotEmpty()) {
        val searchList = viewModel.SearchGrid(searchText.text, pokemonListItem)
        pokemonListItem = searchList.collectAsLazyPagingItems()
    }
    val dayOfWeek = getCurrentDayOfWeek()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = getColorByDayOfWeek(dayOfWeek),

        topBar = {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text(
                            text = "  Pokédex",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Serif
                        )
                    },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.pokeball),
                            contentDescription = "Logo",
                            tint = Color.White,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Search(
                        text = searchText.text,
                        placeholder = "Search......",
                        state = TextState,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    SortList()
                }

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp, top = 130.dp, bottom = 5.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            if (pokemonListItem.loadState.refresh == LoadState.Loading) {
                LazyVerticalGrid(columns = GridCells.Fixed(3), content = {
                    items(20) { index ->
                        ShimerHome(
                            isLoading = pokemonListItem.itemSnapshotList.isEmpty(),
                            contentAfterLoading = {
                            })
                    }
                })
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    content = {
                        items(pokemonListItem.itemCount) { index ->
                            val pokemon = pokemonListItem[index]
                            pokemon?.let {
                                GridItem(navController = navController, item = it)
                            }
                        }
                        when (pokemonListItem.loadState.append) {
                            is LoadState.NotLoading -> Unit
                            LoadState.Loading -> {
                                item(span = { GridItemSpan(3) }) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            LoadingAnimation()
                                        }
                                    }
                                }
                            }

                            is LoadState.Error -> TODO()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }


        }

    }

}
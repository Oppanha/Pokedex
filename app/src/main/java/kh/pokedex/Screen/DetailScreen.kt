package kh.pokedex.Screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kh.pokedex.Component.CapitalizeLetter
import kh.pokedex.Screen.Widget.Attribute
import kh.pokedex.Screen.Widget.TypeBox
import kh.pokedex.Component.formatNumber
import kh.pokedex.Extension.getColorByType
import kh.pokedex.Extension.getImageFromName
import kh.pokedex.Screen.Widget.PokemonBaseStat
import kh.pokedex.ViewHolder.DetailViewModel
import kh.pokedex.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    viewModel: DetailViewModel,
    navController: NavHostController,
) {

    val dataState by remember { viewModel.dataState }
    val list = dataState.data
    val pagerState: PagerState = rememberPagerState(
        pageCount = { list?.name?.length ?: 0 }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = list?.types?.first()?.type?.name?.getColorByType() ?: Transparent,

        topBar = {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = list?.forms?.first()?.name?.CapitalizeLetter() ?: "",
                            style = TextStyle(
                                color = White,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif
                            )
                        )
                        list?.id?.let {
                            formatNumber(number = it)
                        }

                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    })
                    {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = White,
                            modifier = Modifier.size(
                                35.dp
                            )
                        )
                    }
                }
            )
        }
    ) {
        //Detail Info
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Transparent)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(White)

                ) {
                    //TypeBox list
                    list?.let {
                        Spacer(modifier = Modifier.height(70.dp))
                        TypeBox(it)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "About",
                            style = MaterialTheme.typography.titleLarge,
                            color = it.types.first().type.name.getColorByType(),
                            fontWeight = FontWeight.ExtraBold
                        )

                        //Atrribute
                        Spacer(modifier = Modifier.height(20.dp))
                        Attribute(it)
                        Text(
                            text = "There is a plant seed on its back right from the day this Pokémon is born. The seed slowly grows larger.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 10.dp)

                        )
//                    BaseState
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Base Stats",
                            style = MaterialTheme.typography.titleLarge,
                            color = it.types.first().type.name.getColorByType(),
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        if (it != null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            ) {
                                PokemonBaseStat(pokemonInfo = list)
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(200.dp)
                    .border(
                        5.dp,
                        list?.types?.first()?.type?.name?.getColorByType() ?: Black,
                        RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp))
                    .background(White)
                    .align(Alignment.TopCenter)
            ) {
                AsyncImage(
                    model = list?.forms?.first()?.name?.getImageFromName(),
//                    model = state.value.data?.forms?.first()?.name?.getImageFromName(),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

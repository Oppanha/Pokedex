package kh.pokedex.Screen.Widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kh.pokedex.Component.CapitalizeLetter
import kh.pokedex.Extension.getImageFromName
import kh.pokedex.Data.Model.ListItemData.ResultPokemon
import kh.pokedex.Extension.Mypref.getColorByDayOfWeek
import kh.pokedex.Extension.Mypref.getCurrentDayOfWeek


@Composable
fun GridItem(navController: NavController, item: ResultPokemon) {
    val dayofWeek = getCurrentDayOfWeek()
    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(4.dp, getColorByDayOfWeek(dayofWeek), RoundedCornerShape(10.dp))
                .background(Color.White)
                .clickable {
                    val items = navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "DetailsScreen",
                        value = item
                    )
                    navController.navigate("details")
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(

                    model = item.name.getImageFromName(),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(80.dp)
                        .fillMaxSize(),
                    alignment = Alignment.Center

                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = item.name.CapitalizeLetter(),
                    style = TextStyle(color = Color.DarkGray, fontSize = 17.sp)
                )
            }
        }

    }
}
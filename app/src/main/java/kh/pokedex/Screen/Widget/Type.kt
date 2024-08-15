package kh.pokedex.Screen.Widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.pokedex.Component.CapitalizeLetter
import kh.pokedex.Data.Remote.Response.Pokemon
import kh.pokedex.Extension.getColorByType
import kh.pokedex.ui.theme.White

@Composable
fun TypeBox(
    pokemon: Pokemon?
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        pokemon?.types?.forEach { typeEntry ->
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(typeEntry.type.name.getColorByType()),
                contentAlignment = Center
            ) {
                Text(
                    text = typeEntry.type.name.CapitalizeLetter(),
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = White,
                        fontWeight = FontWeight.ExtraBold,
                    )
                )
            }
        }
    }
}
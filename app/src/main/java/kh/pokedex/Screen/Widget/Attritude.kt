package kh.pokedex.Screen.Widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.ui.text.style.TextAlign
import kh.pokedex.Component.CapitalizeLetter
import kh.pokedex.Component.divider
import kh.pokedex.Data.Remote.Response.Pokemon
import kh.pokedex.R
import kh.pokedex.ui.theme.Medium


@Composable
fun Attribute(
    pokemon: Pokemon?
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.weight),
                    contentDescription = "Weight"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "${pokemon?.weight} Kg",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text("Weight", color = Medium)
        }
        divider()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Straighten,
                    contentDescription = "Height"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "${pokemon?.height} m",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text("Height", color = Medium)
        }
        divider()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                pokemon?.moves?.first()?.move?.name?.CapitalizeLetter() ?: "",
                maxLines = 2,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text("Moves", color = Medium)
        }
    }
}


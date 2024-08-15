package kh.pokedex.Component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.pokedex.ui.theme.Medium

@Composable
fun divider() {
    Divider(
        color = Medium,
        modifier = Modifier
            .fillMaxHeight()

            .width(1.dp)

    )
}

@Preview
@Composable
fun prevDivider(){
    divider()
}
package kh.pokedex.Component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import kh.pokedex.ui.theme.Light

@Composable
fun formatNumber(number: Int) {
    val formattedNumber = String.format("%03d", number)
    Text(
        text = "#${formattedNumber}",
        style = MaterialTheme.typography.titleLarge,
        color = Light,
        fontWeight = FontWeight.Bold
    )
}
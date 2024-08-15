package kh.pokedex.Screen.Widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kh.pokedex.Extension.parseStatToColor
import kh.pokedex.Extension.parseStateToAbbr
import kh.pokedex.Data.Remote.Response.Pokemon
import kh.pokedex.ui.theme.Light
import kh.pokedex.ui.theme.Medium


@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: androidx.compose.ui.graphics.Color,
    height: Dp = 28.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0

) {
    var animationPlay by remember {
        mutableStateOf(false)
    }
    val curPercent = animateFloatAsState(
        targetValue = if (animationPlay) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlay = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(
                if (isSystemInDarkTheme()) {
                    Medium
                } else {
                    Light
                }
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(curPercent.value)
                .fillMaxHeight()
                .clip(CircleShape)
                .background(color = statColor)
                .padding(horizontal = 10.dp)
        ) {
            Text(text = statName, fontWeight = FontWeight.Bold)
            Text(
                text = (curPercent.value * statMaxValue).toInt().toString(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PokemonBaseStat(
    pokemonInfo: Pokemon,
    animDelay: Int = 100
) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf {
            it.base_stat
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        for (stat in pokemonInfo.stats) {
            maxBaseStat.let {
                PokemonStat(
                    statName = parseStateToAbbr(stat),
                    statValue = stat.base_stat,
                    statMaxValue = it,
                    statColor = parseStatToColor(stat),
                    animDelay = 100
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


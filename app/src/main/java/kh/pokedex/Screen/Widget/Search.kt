package kh.pokedex.Screen.Widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kh.pokedex.R
import kh.pokedex.ui.theme.Primary

@Composable
fun Search(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    state: MutableState<TextFieldValue>
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(300.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(20.dp)
            )
            .background(
                color = Color.White
            )
    ) {
        TextField(
            value = state.value,
            onValueChange = { state.value = it },
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Primary,
                    contentDescription = "Search...."
                )
            },
            placeholder = {
                Text(text = placeholder, color = Color.LightGray)
            },

            )

    }
}

@Composable
fun SortList() {
    var isChange by remember {
        mutableStateOf(true)
    }
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.White)

    ) {
        IconButton(onClick = {
            isChange = !isChange
        }) {
            val iconId = if (isChange) R.drawable.text_format else R.drawable.tag
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = if (isChange) "Sort Ascending" else "Sort Descending",
                modifier = Modifier.size(25.dp),
                tint = Primary
            )
        }
    }
}


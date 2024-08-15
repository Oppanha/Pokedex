package kh.pokedex.Extension

import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier
import java.time.LocalDate
import java.util.Calendar

object Mypref {
    fun getCurrentDay(context: Context): String? {
        val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return prefs.getString("day", null)
    }

    fun setCurrentDay(context: Context, day: String) {
        val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString("day", day)
            apply()
        }
    }

    fun getCurrentDayOfWeek(): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return when (dayOfWeek) {
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            Calendar.SUNDAY -> "Sunday"
            else -> "Unknown"
        }
    }

    fun getColorByDayOfWeek(dayOfWeek: String): Color {
        return when (dayOfWeek) {
            "Monday" -> Color.Blue
            "Tuesday" -> Color.Green
            "Wednesday" -> Color.Yellow
            "Thursday" -> Color.Red
            "Friday" -> Color.Magenta
            "Saturday" -> Color.Cyan
            "Sunday" -> Color.Gray
            else -> Color.DarkGray
        }
    }
}


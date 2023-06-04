package uz.gita.todoappexam.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun TodoItem(
    title: String,
    description: String,
    date: String,
    time: String,
    modifier: Modifier = Modifier
) {

    val userSelectedDateTime = Calendar.getInstance()

    val chosenYear = date.substring(0, 4).toInt()
    val chosenMonth = date.substring(5, 7).toInt()
    val chosenDay = date.substring(8).toInt()

    val chosenHour = time.substring(0, 2).toInt()
    val chosenMin = time.substring(3).toInt()

    userSelectedDateTime.set(
        chosenYear,
        chosenMonth,
        chosenDay,
        chosenHour,
        chosenMin
    )
    val todayDateTime = Calendar.getInstance()
    todayDateTime.set(
        LocalDateTime.now().year,
        LocalDate.now().monthValue,
        LocalDateTime.now().dayOfMonth,
        LocalDateTime.now().hour, LocalDateTime.now().minute
    )

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                style = if (userSelectedDateTime < todayDateTime) TextStyle(
                    textDecoration = TextDecoration.LineThrough
                ) else TextStyle()
            )

            Text(
                text = description,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = date,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )

                Text(
                    text = time,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
}

@Composable
@Preview
fun ContactItemPreview() {
    TodoItem(title = "David", description = "Watson", date = "2023-06-04", time = "18:00")
}
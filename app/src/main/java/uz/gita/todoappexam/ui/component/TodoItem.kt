package uz.gita.todoappexam.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.todoappexam.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun TodoItem(
    id: Long,
    title: String,
    description: String,
    date: String,
    time: String,
    category: String,
    isDone: Boolean,
    color: Int,
    modifier: Modifier = Modifier,
    onClick: ((Boolean, Long) -> Unit)
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

    var checked by remember { mutableStateOf(isDone) }

    Card(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = color))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = checked,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 6.dp),
                onCheckedChange = {
                    checked = !checked
                    onClick.invoke(checked, id)
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .width(0.dp)
                            .weight(1f)
                            .padding(end = 8.dp),
                        text = title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        style = if (isDone) TextStyle(
                            textDecoration = TextDecoration.LineThrough
                        ) else TextStyle(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = category,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .width(0.dp)
                            .weight(1f)
                            .padding(end = 14.dp),
                        text = description,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = time,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ContactItemPreview() {
    TodoItem(
        id = 0,
        title = "David", description = "Lorem ipsum dolores programming development shit",
        date = "2023-06-04", time = "18:00",
        category = "Home", isDone = true,
        color = R.color.orange, modifier = Modifier.padding(16.dp)
    ) { state, id ->
    }
}
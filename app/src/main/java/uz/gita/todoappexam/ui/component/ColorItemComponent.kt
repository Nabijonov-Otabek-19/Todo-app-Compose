package uz.gita.todoappexam.ui.component


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.todoappexam.R

@Composable
fun ColorItem(color: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = color))
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun ColorItemPreview() {
    ColorItem(color = R.color.yellow, Modifier.padding(8.dp))
}
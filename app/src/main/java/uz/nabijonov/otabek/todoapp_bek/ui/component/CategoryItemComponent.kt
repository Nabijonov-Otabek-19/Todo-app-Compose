package uz.nabijonov.otabek.todoapp_bek.ui.component


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nabijonov.otabek.todoapp_bek.R

@Composable
fun CategoryItem(
    color: Int,
    category: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = color))
    ) {
        Text(
            text = category,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview() {
    CategoryItem(color = R.color.red, category = "Home", Modifier.padding(8.dp))
}
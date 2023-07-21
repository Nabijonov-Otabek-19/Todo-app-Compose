package uz.nabijonov.otabek.todoapp_bek.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import uz.nabijonov.otabek.todoapp_bek.utils.categories
import uz.nabijonov.otabek.todoapp_bek.utils.colors

@Composable
fun CategoryPickerDialog(
    categories: List<String>,
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismiss) {
        Surface(modifier = modifier.padding(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryItem(
                        color = colors[0],
                        category = categories[0],
                        modifier = Modifier.clickable { onSelected(categories[0]) })

                    CategoryItem(
                        color = colors[1],
                        category = categories[1],
                        modifier = Modifier.clickable { onSelected(categories[1]) })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryItem(
                        color = colors[2],
                        category = categories[2],
                        modifier = Modifier.clickable { onSelected(categories[2]) })

                    CategoryItem(
                        color = colors[3],
                        category = categories[3],
                        modifier = Modifier.clickable { onSelected(categories[3]) })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CategoryItem(
                        color = colors[4],
                        category = categories[4],
                        modifier = Modifier.clickable { onSelected(categories[4]) })

                    CategoryItem(
                        color = colors[5],
                        category = categories[5],
                        modifier = Modifier.clickable { onSelected(categories[5]) })
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CategoryPickerDialogPreview() {
    CategoryPickerDialog(
        categories = categories,
        modifier = Modifier.padding(8.dp),
        onSelected = {},
        onDismiss = {}
    )
}
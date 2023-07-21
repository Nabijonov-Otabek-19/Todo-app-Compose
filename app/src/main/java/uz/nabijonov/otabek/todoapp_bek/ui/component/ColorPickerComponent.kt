package uz.nabijonov.otabek.todoapp_bek.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import uz.nabijonov.otabek.todoapp_bek.utils.colors

@Composable
fun ColorPickerDialog(
    colors: List<Int>,
    onColorSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismiss) {
        Surface(modifier = Modifier.padding(16.dp)) {

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ColorItem(
                        color = colors[0],
                        modifier = Modifier.clickable { onColorSelected(colors[0]) })

                    ColorItem(
                        color = colors[1],
                        modifier = Modifier.clickable { onColorSelected(colors[1]) })

                    ColorItem(
                        color = colors[2],
                        modifier = Modifier.clickable { onColorSelected(colors[2]) })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ColorItem(
                        color = colors[3],
                        modifier = Modifier.clickable { onColorSelected(colors[3]) })

                    ColorItem(
                        color = colors[4],
                        modifier = Modifier.clickable { onColorSelected(colors[4]) })

                    ColorItem(
                        color = colors[5],
                        modifier = Modifier.clickable { onColorSelected(colors[5]) })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorPickerDialogPreview() {
    ColorPickerDialog(colors = colors, onColorSelected = {}) {

    }
}

package uz.gita.todoappexam.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog


/*@Composable
fun ColorPickerComponent(
    onClick: (Int) -> Unit
) {

    val dialogState = rememberMaterialDialogState()

    MaterialDialog(dialogState = dialogState) {

    }
}*/

@Composable
fun ColorPickerDialog(
    colors: List<Int>,
    onColorSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismiss) {
        Surface(modifier = Modifier.padding(16.dp)) {
            LazyColumn {
                items(colors) { color ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onColorSelected(color) }
                            .padding(8.dp)
                            .height(48.dp)
                            .background(color = colorResource(id = color))
                    )
                }
            }
        }
    }
}

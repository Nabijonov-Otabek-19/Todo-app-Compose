package uz.gita.todoappexam.ui.component


import android.annotation.SuppressLint
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import uz.gita.todoappexam.R
import uz.gita.todoappexam.data.common.TodoData
import uz.gita.todoappexam.ui.screen.home.HomeViewContract
import uz.gita.todoappexam.utils.toast
import uz.gita.todoappexam.workmanager.cancelWork
import java.util.UUID


@Composable
fun AlertDialogComponent(
    onEventDispatcher: (intent: HomeViewContract.Intent) -> Unit,
    data: TodoData,
    show: Boolean,
    showDialog: MutableState<Boolean>
) {
    val context = LocalContext.current
    var openDialog by remember { mutableStateOf(show) }

    if (openDialog) {

        AlertDialog(
            properties = DialogProperties(dismissOnClickOutside = false),
            onDismissRequest = { openDialog = false },
            title = { Text(text = "Warning", color = Color.Black) },
            text = { Text("Do you want to delete ?", color = Color.Black) },

            confirmButton = {
                TextButton(
                    onClick = {
                        cancelWork(context, data.workId)
                        onEventDispatcher(HomeViewContract.Intent.Delete(data))
                        openDialog = false
                        showDialog.value = false
                        toast(context, "Todo deleted")
                    }
                ) { Text("Confirm", color = Color.Black) }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog = false
                    showDialog.value = false
                })
                { Text("Dismiss", color = Color.Black) }
            },
            containerColor = colorResource(id = R.color.blue),
            textContentColor = Color.Black
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showBackground = true)
fun CustomAlertDialogPreview() {
    AlertDialogComponent(
        onEventDispatcher = {},
        data = TodoData(
            0, "", "",
            "", "", "", false, 0, UUID.randomUUID()
        ),
        show = false,
        showDialog = mutableStateOf(false)
    )
}
package uz.gita.todoappexam.ui.screen.add

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import uz.gita.todoappexam.data.common.TodoData
import uz.gita.todoappexam.ui.component.MyTextField
import uz.gita.todoappexam.ui.theme.TodoAppTheme
import uz.gita.todoappexam.workmanager.setWork
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class AddScreen(private val updateData: TodoData?) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: AddEditContract.ViewModel = getViewModel<AddTodoViewModelImpl>()

        TodoAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                AddContactScreenContent(viewModel::onEventDispatcher, updateData)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreenContent(
    onEventDispatcher: (AddEditContract.Intent) -> Unit,
    updateData: TodoData?
) {
    var title by remember { mutableStateOf(updateData?.title ?: "") }
    var description by remember { mutableStateOf(updateData?.description ?: "") }
    var date by remember { mutableStateOf(updateData?.date ?: "") }
    var time by remember { mutableStateOf(updateData?.time ?: "") }
    val workId by remember { mutableStateOf(updateData?.workId ?: UUID.randomUUID()) }

    val isUpdate = updateData != null

    val context = LocalContext.current
    val navigator = LocalNavigator.currentOrThrow
    val currentDate = LocalDate.now()

    val focusManager = LocalFocusManager.current
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    Scaffold(topBar = { TopAppBar(title = { Text("Add Todo") }) })
    { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Title")
            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "Title", value = title,
                onValueChange = { title = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Description")
            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "Description", value = description,
                onValueChange = { description = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Date")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp)
                    .clip(RoundedCornerShape(size = 16.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFFAAE9E6),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = date,
                    onValueChange = { focusManager.clearFocus() },
                    placeholder = { Text(text = "Date") },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Gray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            dateDialogState.show()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = ""
                            )
                        }
                    },
                    keyboardActions = KeyboardActions(onSearch = {
                        focusManager.clearFocus()
                    }),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    )
                )
            }

            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Time")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp)
                    .clip(RoundedCornerShape(size = 16.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFFAAE9E6),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = time,
                    onValueChange = { focusManager.clearFocus() },
                    placeholder = { Text(text = "Time") },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Gray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.White
                    ),
                    trailingIcon = {
                        IconButton(onClick = { timeDialogState.show() }) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = ""
                            )
                        }
                    },

                    keyboardActions = KeyboardActions(onSearch = {
                        focusManager.clearFocus()
                    }),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    )
                )
            }

            ElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
                onClick = {
                    if (!isUpdate && title.isNotEmpty() && description.isNotEmpty()
                        && time.isNotEmpty() && date.isNotEmpty()
                    ) {
                        onEventDispatcher(
                            AddEditContract.Intent.AddContact(
                                TodoData(
                                    title = title,
                                    description = description,
                                    date = date,
                                    time = time,
                                    workId = workId
                                )
                            )
                        )
                        setWork(context, date, time, title, description, workId)
                        navigator.pop()

                    }
                    else if (isUpdate && title.isNotEmpty() && description.isNotEmpty()
                        && time.isNotEmpty() && date.isNotEmpty()
                    ) {
                        onEventDispatcher(
                            AddEditContract.Intent.UpdateContact(
                                TodoData(
                                    updateData!!.id,
                                    title,
                                    description,
                                    date,
                                    time,
                                    workId
                                )
                            )
                        )
                        setWork(context, date, time, title, description, workId)
                        navigator.pop()

                    }
                }) {
                Text(text = "Add")
            }
        }

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok") {}
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
                yearRange = IntRange(LocalDate.now().year, LocalDate.now().year + 20),
                allowedDateValidator = {
                    it.monthValue >= currentDate.monthValue
                            && it.dayOfMonth >= currentDate.dayOfMonth
                }
            ) {
                date = it.toString()
            }
        }

        MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(text = "Ok") {}
                negativeButton(text = "Cancel")
            }
        ) {
            timepicker(
                initialTime = LocalTime.NOON,
                title = "Pick a time"
            ) {
                time = it.toString()
            }
        }
    }
}
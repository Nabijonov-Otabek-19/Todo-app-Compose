package uz.gita.todoappexam.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.todoappexam.R
import uz.gita.todoappexam.data.common.TodoData
import uz.gita.todoappexam.navigation.AppScreen
import uz.gita.todoappexam.ui.component.LoadingComponent
import uz.gita.todoappexam.ui.component.TodoItem
import uz.gita.todoappexam.ui.theme.TodoAppTheme
import uz.gita.todoappexam.utils.toast
import uz.gita.todoappexam.workmanager.cancelWork
import java.util.UUID

class HomeScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: HomeViewContract.ViewModel = getViewModel<HomeViewModelImpl>()
        val uiState = viewModel.collectAsState()
        val context = LocalContext.current

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is HomeViewContract.SideEffect.Toast -> {
                    toast(context, sideEffect.message)
                }
            }
        }

        TodoAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = { TopBar() }) {
                    HomeContactScreenContent(
                        uiState = uiState,
                        viewModel::onEventDispatcher,
                        Modifier.padding(it)
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp)
            .background(color = Color.White)
            .height(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Todo List", fontSize = 24.sp, color = Color.Black)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContactScreenContent(
    uiState: State<HomeViewContract.UIState>,
    onEventDispatcher: (intent: HomeViewContract.Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    val showDialog = remember { mutableStateOf(false) }
    val data = remember {
        mutableStateOf(
            TodoData(
                -1, "", "",
                "", "", "", false, R.color.white, UUID.randomUUID()
            )
        )
    }

    val context = LocalContext.current

    if (showDialog.value) {
        AlertDialogComponent(
            onEventDispatcher = onEventDispatcher,
            data = data.value,
            true,
            showDialog
        )
    }

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        when (uiState.value) {
            HomeViewContract.UIState.Loading -> {
                LoadingComponent()
                onEventDispatcher.invoke(HomeViewContract.Intent.LoadTodos)
            }

            is HomeViewContract.UIState.PrepareData -> {
                val todoData = (uiState.value as HomeViewContract.UIState.PrepareData).todos

                if (todoData.isEmpty()) {
                    Image(
                        modifier = Modifier
                            .size(180.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.timeline),
                        contentDescription = null
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        content = {

                            item {
                                Text(
                                    modifier = Modifier.padding(vertical = 16.dp),
                                    text = "Upcoming",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            items(todoData.size) {
                                Spacer(modifier = Modifier.size(8.dp))

                                TodoItem(
                                    title = todoData[it].title,
                                    description = todoData[it].description,
                                    date = todoData[it].date,
                                    time = todoData[it].time,
                                    category = todoData[it].category,
                                    isDone = todoData[it].isDone,
                                    color = todoData[it].color,
                                    modifier = Modifier.combinedClickable(
                                        onClick = {
                                            onEventDispatcher(
                                                HomeViewContract.Intent.OpenEditContact(todoData[it])
                                            )
                                        },
                                        onLongClick = {
                                            data.value = todoData[it]
                                            showDialog.value = true
                                        }
                                    )
                                ) { state ->
                                    toast(context, state.toString())
                                }
                            }
                        })
                }

            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.Blue,
            onClick = {
                onEventDispatcher(HomeViewContract.Intent.OpenAddContact)
            }) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }
    }
}

@Composable
fun AlertDialogComponent(
    onEventDispatcher: (intent: HomeViewContract.Intent) -> Unit,
    data: TodoData,
    show: Boolean,
    showDialog: MutableState<Boolean>
) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(show) }

    if (openDialog.value) {
        AlertDialog(
            properties = DialogProperties(dismissOnClickOutside = false),
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Warning", color = Color.White) },
            text = { Text("Do you want to delete ?", color = Color.White) },

            confirmButton = {
                TextButton(
                    onClick = {
                        cancelWork(context, data.workId)
                        onEventDispatcher(HomeViewContract.Intent.Delete(data))
                        openDialog.value = false
                        showDialog.value = false
                        Toast.makeText(context, "Item deleted", Toast.LENGTH_LONG).show()
                    }
                ) { Text("Confirm", color = Color.White) }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    showDialog.value = false
                })
                { Text("Dismiss", color = Color.White) }
            },
            containerColor = colorResource(id = R.color.teal_200),
            textContentColor = Color.White
        )
    }
}
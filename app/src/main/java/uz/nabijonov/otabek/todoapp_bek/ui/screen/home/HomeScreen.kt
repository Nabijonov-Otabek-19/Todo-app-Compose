package uz.nabijonov.otabek.todoapp_bek.ui.screen.home

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.nabijonov.otabek.todoapp_bek.R
import uz.nabijonov.otabek.todoapp_bek.data.common.TodoData
import uz.nabijonov.otabek.todoapp_bek.navigation.AppScreen
import uz.nabijonov.otabek.todoapp_bek.ui.component.AlertDialogComponent
import uz.nabijonov.otabek.todoapp_bek.ui.component.LoadingComponent
import uz.nabijonov.otabek.todoapp_bek.ui.component.TodoItem
import uz.nabijonov.otabek.todoapp_bek.ui.theme.TodoAppTheme
import uz.nabijonov.otabek.todoapp_bek.utils.toast
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

    if (showDialog.value) {
        AlertDialogComponent(
            onEventDispatcher = onEventDispatcher,
            data = data.value,
            true,
            showDialog
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        when (uiState.value) {
            HomeViewContract.UIState.Loading -> {
                LoadingComponent()
                onEventDispatcher.invoke(HomeViewContract.Intent.LoadTodos)
            }

            is HomeViewContract.UIState.PrepareData -> {
                val todoList = (uiState.value as HomeViewContract.UIState.PrepareData).todoList

                if (todoList.isEmpty()) {
                    Image(
                        modifier = Modifier
                            .size(180.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.timeline),
                        contentDescription = null
                    )
                } else {

                    Column(modifier = Modifier.fillMaxWidth()) {
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

                                items(todoList.size) {
                                    Spacer(modifier = Modifier.size(8.dp))

                                    if (!todoList[it].isDone) {
                                        TodoItem(
                                            id = todoList[it].id,
                                            title = todoList[it].title,
                                            description = todoList[it].description,
                                            date = todoList[it].date,
                                            time = todoList[it].time,
                                            category = todoList[it].category,
                                            isDone = todoList[it].isDone,
                                            color = todoList[it].color,
                                            modifier = Modifier.combinedClickable(
                                                onClick = {
                                                    onEventDispatcher(
                                                        HomeViewContract.Intent.OpenEditContact(
                                                            todoList[it]
                                                        )
                                                    )
                                                },
                                                onLongClick = {
                                                    data.value = todoList[it]
                                                    showDialog.value = true
                                                }
                                            )
                                        ) { state, todoId ->
                                            onEventDispatcher(
                                                HomeViewContract.Intent.UpdateState(
                                                    state,
                                                    todoId
                                                )
                                            )
                                            onEventDispatcher(HomeViewContract.Intent.LoadTodos)
                                        }
                                    }
                                }
                            })

                        LazyColumn(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            content = {

                                item {
                                    Text(
                                        modifier = Modifier.padding(vertical = 16.dp),
                                        text = "Completed",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                items(todoList.size) {
                                    Spacer(modifier = Modifier.size(8.dp))

                                    if (todoList[it].isDone) {
                                        TodoItem(
                                            id = todoList[it].id,
                                            title = todoList[it].title,
                                            description = todoList[it].description,
                                            date = todoList[it].date,
                                            time = todoList[it].time,
                                            category = todoList[it].category,
                                            isDone = todoList[it].isDone,
                                            color = todoList[it].color,
                                            modifier = Modifier.combinedClickable(
                                                onClick = {
                                                    onEventDispatcher(
                                                        HomeViewContract.Intent.OpenEditContact(
                                                            todoList[it]
                                                        )
                                                    )
                                                },
                                                onLongClick = {
                                                    data.value = todoList[it]
                                                    showDialog.value = true
                                                }
                                            )
                                        ) { state, todoId ->
                                            onEventDispatcher(
                                                HomeViewContract.Intent.UpdateState(state, todoId)
                                            )
                                            onEventDispatcher(HomeViewContract.Intent.LoadTodos)
                                        }
                                    }
                                }
                            })
                    }
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
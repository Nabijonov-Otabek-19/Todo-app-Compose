package uz.gita.todoappexam.ui.screen.home

import uz.gita.todoappexam.data.common.TodoData
import uz.gita.todoappexam.navigation.AppNavigator
import uz.gita.todoappexam.ui.screen.add.AddScreen
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeViewContract.Direction {

    override suspend fun navigateToAddEditScreen(data: TodoData?) {
        appNavigator.navigateTo(AddScreen(data))
    }
}
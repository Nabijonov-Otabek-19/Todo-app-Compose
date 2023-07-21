package uz.nabijonov.otabek.todoapp_bek.ui.screen.home

import uz.nabijonov.otabek.todoapp_bek.data.common.TodoData
import uz.nabijonov.otabek.todoapp_bek.navigation.AppNavigator
import uz.nabijonov.otabek.todoapp_bek.ui.screen.add.AddScreen
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeViewContract.Direction {

    override suspend fun navigateToAddEditScreen(data: TodoData?) {
        appNavigator.navigateTo(AddScreen(data))
    }
}
package uz.gita.contactappcompose.ui.screen.home

import uz.gita.contactappcompose.data.common.ContactData
import uz.gita.contactappcompose.navigation.AppNavigator
import uz.gita.contactappcompose.ui.screen.addcontact.AddScreen
import javax.inject.Inject


class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeViewContract.Direction {

    override suspend fun navigateToAddEditScreen(data: ContactData?) {
        appNavigator.navigateTo(AddScreen(data))
    }
}
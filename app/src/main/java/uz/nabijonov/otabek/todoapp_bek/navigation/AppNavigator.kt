package uz.nabijonov.otabek.todoapp_bek.navigation


interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}
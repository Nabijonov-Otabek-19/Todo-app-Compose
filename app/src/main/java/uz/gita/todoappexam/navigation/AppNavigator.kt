package uz.gita.todoappexam.navigation


interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}
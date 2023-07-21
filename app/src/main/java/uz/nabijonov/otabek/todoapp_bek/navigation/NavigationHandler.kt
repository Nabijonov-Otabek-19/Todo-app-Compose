package uz.nabijonov.otabek.todoapp_bek.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationHandler {
    val navigationBuffer: Flow<NavigationArg>
}
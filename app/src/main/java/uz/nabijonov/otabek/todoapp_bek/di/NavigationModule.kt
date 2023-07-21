package uz.nabijonov.otabek.todoapp_bek.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.todoapp_bek.navigation.AppNavigator
import uz.nabijonov.otabek.todoapp_bek.navigation.NavigationDispatcher
import uz.nabijonov.otabek.todoapp_bek.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindAppNavigator(impl: NavigationDispatcher): AppNavigator

    @Binds
    fun bindNavigationHandler(impl: NavigationDispatcher): NavigationHandler
}
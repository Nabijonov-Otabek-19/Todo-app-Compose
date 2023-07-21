package uz.nabijonov.otabek.todoapp_bek.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.nabijonov.otabek.todoapp_bek.ui.screen.home.HomeDirection
import uz.nabijonov.otabek.todoapp_bek.ui.screen.home.HomeViewContract
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds Singleton]
    fun bindHomeScreenDirection(impl: HomeDirection): HomeViewContract.Direction
}
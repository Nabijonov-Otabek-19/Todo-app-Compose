package uz.gita.todoappexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.todoappexam.ui.screen.home.HomeDirection
import uz.gita.todoappexam.ui.screen.home.HomeViewContract
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    @Singleton
    fun bindHomeScreenDirection(impl: HomeDirection): HomeViewContract.Direction
}
package uz.gita.contactappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.contactappcompose.ui.screen.home.HomeDirection
import uz.gita.contactappcompose.ui.screen.home.HomeViewContract
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    @Singleton
    fun bindScreen1Direction(impl: HomeDirection): HomeViewContract.Direction
}
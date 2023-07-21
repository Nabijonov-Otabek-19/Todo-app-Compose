package uz.nabijonov.otabek.todoapp_bek.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.todoapp_bek.domain.repository.AppRepositoryImpl
import uz.nabijonov.otabek.todoapp_bek.domain.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}
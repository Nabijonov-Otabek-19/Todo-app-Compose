package uz.gita.contactappcompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappcompose.data.source.local.dao.ContactDao
import uz.gita.contactappcompose.data.source.local.db.AppDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "contactDatabase"
    ).allowMainThreadQueries().build()

    @[Provides Singleton]
    fun provideContactDao(db: AppDatabase): ContactDao = db.getContactDao()
}
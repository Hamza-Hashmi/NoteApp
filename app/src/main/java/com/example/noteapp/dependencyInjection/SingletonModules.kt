package com.example.noteapp.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.noteapp.adapters.NotesAdapter
import com.example.noteapp.repository.DatabaseRepo
import com.example.noteapp.extensions.databaseName
import com.example.noteapp.roomDB.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SingletonModules {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesAdapter() = NotesAdapter()


    @Provides
    @Singleton
    fun provideDatabaseRepo(db:AppDatabase) = DatabaseRepo(db)
}
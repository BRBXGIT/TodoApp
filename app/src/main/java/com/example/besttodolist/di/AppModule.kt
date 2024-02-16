package com.example.besttodolist.di

import android.content.Context
import androidx.room.Room
import com.example.besttodolist.data.TodoDao
import com.example.besttodolist.data.TodoDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Provide TodoDb
    @Provides
    @Singleton
    fun provideTodoDao(@ApplicationContext appContext: Context): TodoDao {
        return Room.databaseBuilder(
            appContext,
            TodoDb::class.java,
            "TodoDb"
        ).build().TodoDao()
    }
}
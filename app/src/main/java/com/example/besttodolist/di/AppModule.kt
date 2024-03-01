package com.example.besttodolist.di

import android.content.Context
import androidx.room.Room
import com.example.besttodolist.data.TodoDao
import com.example.besttodolist.data.TodoDb
import com.example.besttodolist.presentation.sign_in.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
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

    //Provide googleAuthUiClient
    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(@ApplicationContext appContext: Context): GoogleAuthUiClient {
        return GoogleAuthUiClient(
            context = appContext,
            oneTapClient = Identity.getSignInClient(appContext)
        )
    }
}
package com.omaroid.caloriestracker.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.omaroid.core.data.preferences.DefaultPreferences
import com.omaroid.core.domain.preferences.Preferences
import com.omaroid.core.domain.use_cases.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFilterOutDigits(): FilterOutDigits {
        return FilterOutDigits()
    }
}
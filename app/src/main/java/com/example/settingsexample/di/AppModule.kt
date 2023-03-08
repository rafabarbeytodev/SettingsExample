package com.example.settingsexample.di

import android.content.Context
import com.example.settingsexample.data.DataStoreRepository
import com.example.settingsexample.data.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*****
 * Proyect: SettingsExample
 * Package: com.example.settingsexample.di
 *
 * Created by Rafael Barbeyto Torrellas on 08/03/2023 at 10:18
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(app)
}
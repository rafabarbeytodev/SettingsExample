package com.example.settingsexample.data

import kotlinx.coroutines.flow.Flow

/*****
 * Proyect: SettingsExample
 * Package: com.example.settingsexample.data
 *
 * Created by Rafael Barbeyto Torrellas on 08/03/2023 at 9:48
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
interface DataStoreRepository {
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun putInt(key: String, value: Int)
    suspend fun getSettings(): Flow<SettingsModel?>
}
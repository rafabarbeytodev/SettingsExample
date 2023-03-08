package com.example.settingsexample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.settingsexample.data.DataStoreRepository
import com.example.settingsexample.data.SettingsModel
import com.example.settingsexample.utils.KEY_BLUETOOTH
import com.example.settingsexample.utils.KEY_DARK_MODE
import com.example.settingsexample.utils.KEY_VIBRATION
import com.example.settingsexample.utils.VOLUME_LVL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/*****
 * Proyect: SettingsExample
 * Package: com.example.settingsexample
 *
 * Created by Rafael Barbeyto Torrellas on 08/03/2023 at 9:34
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: DataStoreRepository
): ViewModel() {

    fun saveBluetooth(value: Boolean) {
        viewModelScope.launch {
            repository.putBoolean(KEY_BLUETOOTH, value)
        }
    }

    fun getSettings(): Flow<SettingsModel?> = runBlocking {
        repository.getSettings()
    }

    fun saveVibration(value: Boolean) {
        viewModelScope.launch {
            repository.putBoolean(KEY_VIBRATION, value)
        }
    }

    fun saveDarkMode(value: Boolean) {
        viewModelScope.launch {
            repository.putBoolean(KEY_DARK_MODE, value)
        }
    }

    fun saveVolume(value: Int) {
        viewModelScope.launch {
            repository.putInt(VOLUME_LVL, value)
        }
    }

}
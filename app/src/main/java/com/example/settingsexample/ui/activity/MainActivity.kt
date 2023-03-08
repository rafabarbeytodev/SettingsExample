package com.example.settingsexample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.example.settingsexample.data.SettingsModel
import com.example.settingsexample.databinding.ActivityMainBinding
import com.example.settingsexample.ui.viewmodel.MainViewModel
import com.example.settingsexample.utils.KEY_BLUETOOTH
import com.example.settingsexample.utils.KEY_DARK_MODE
import com.example.settingsexample.utils.KEY_VIBRATION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mVmMain by viewModels<MainViewModel>()

    private lateinit var mBinding: ActivityMainBinding
    private var firstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect { settingsModel ->
                if (settingsModel != null) {
                    runOnUiThread {
                        mBinding.swVibration.isChecked = settingsModel.vibration
                        mBinding.swBluetooth.isChecked = settingsModel.bluetooth
                        mBinding.swDarkMode.isChecked = settingsModel.darkMode
                        mBinding.rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime = !firstTime
                    }
                }
            }
        }
        initUI()
    }

    private fun initUI() {
        mBinding.rsVolume.addOnChangeListener { _, value, _ ->
            saveVolume(value.toInt())
        }
        mBinding.swBluetooth.setOnCheckedChangeListener { _, value ->
            saveOptions(KEY_BLUETOOTH, value)
        }
        mBinding.swVibration.setOnCheckedChangeListener { _, value ->
            saveOptions(KEY_VIBRATION, value)
        }
        mBinding.swDarkMode.setOnCheckedChangeListener { _, value ->
            if (value) enableDarkMode() else disableDarkMode()
            saveOptions(KEY_DARK_MODE, value)
        }
    }

    private fun saveVolume(value: Int) {
        mVmMain.saveVolume(value)
    }

    private fun saveOptions(key: String, value: Boolean) {
        when (key) {
            KEY_BLUETOOTH -> mVmMain.saveBluetooth(value)
            KEY_VIBRATION -> mVmMain.saveVibration(value)
            KEY_DARK_MODE -> mVmMain.saveDarkMode(value)
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {
        return mVmMain.getSettings()
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}
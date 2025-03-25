package com.mustafin.ui_components.presentation.vibration

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.VibratorManager

/*
Class that helps to use Vibrator class.
!!! Device with api < 31 won't vibrate because of haptic absence.
*/
class CustomVibrationManager(context: Context) {
    private val vibrator =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        } else {
            null
        }

    fun shortSingleVibration() {
        if (vibrator != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val effect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
            vibrator.vibrate(effect)
        }
    }

    fun shortDoubleVibration() {
        if (vibrator != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val effect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
            vibrator.vibrate(effect)
        }
    }
}

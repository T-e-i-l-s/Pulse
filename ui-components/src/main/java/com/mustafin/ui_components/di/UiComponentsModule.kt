package com.mustafin.ui_components.di

import com.mustafin.ui_components.presentation.vibration.CustomVibrationManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val uiComponentsModule = module {
    singleOf(::CustomVibrationManager)
}
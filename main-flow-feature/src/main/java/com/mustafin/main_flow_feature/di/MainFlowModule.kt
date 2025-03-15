package com.mustafin.main_flow_feature.di

import androidx.room.Room
import com.google.gson.Gson
import com.mustafin.main_flow_feature.data.repositories.requestsRepository.RequestsRepositoryImpl
import com.mustafin.main_flow_feature.data.source.local.db.AppDatabase
import com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.AddRequestScreenViewModel
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.HomeScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainFlowModule = module {
    single { Gson() }
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "pulse_db"
        ).build()
    }
    single { get<AppDatabase>().requestsDao() }
    singleOf(::RequestsRepositoryImpl)
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::AddRequestScreenViewModel)
}
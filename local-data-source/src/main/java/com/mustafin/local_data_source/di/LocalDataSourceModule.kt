package com.mustafin.local_data_source.di

import androidx.room.Room
import com.google.gson.Gson
import com.mustafin.local_data_source.data.local.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val localDataSourceModule = module {
    // General
    singleOf(::Gson)

    // Room
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "pulse_db"
        ).build()
    }
    single { get<AppDatabase>().requestsDao() }
}
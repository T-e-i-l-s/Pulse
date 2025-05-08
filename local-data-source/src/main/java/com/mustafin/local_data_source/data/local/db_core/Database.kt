package com.mustafin.local_data_source.data.local.db_core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mustafin.local_data_source.data.local.requestsSource.RequestsDao
import com.mustafin.local_data_source.data.local.requestsSource.RequestsEntity
import com.mustafin.local_data_source.data.local.typeConverters.HttpRequestConverters
import com.mustafin.local_data_source.data.local.typeConverters.ResponseStatusConverters

/* Main database of this app */
@Database(entities = [RequestsEntity::class], version = 2)
@TypeConverters(
    HttpRequestConverters::class,
    ResponseStatusConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun requestsDao(): RequestsDao
}
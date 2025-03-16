package com.mustafin.main_flow_feature.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mustafin.main_flow_feature.data.source.local.requestsSource.RequestsDao
import com.mustafin.main_flow_feature.data.source.local.requestsSource.RequestsEntity
import com.mustafin.main_flow_feature.data.source.local.typeConverters.HttpRequestConverters
import com.mustafin.main_flow_feature.data.source.local.typeConverters.ResponseStatusConverters

/* Main database of this app */
@Database(entities = [RequestsEntity::class], version = 1)
@TypeConverters(
    HttpRequestConverters::class,
    ResponseStatusConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun requestsDao(): RequestsDao
}
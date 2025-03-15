package com.mustafin.main_flow_feature.data.source.local.requestsSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafin.main_flow_feature.data.source.local.db.Tables

/* Dao-interface of requests table */
@Dao
interface RequestsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: RequestsEntity)

    @Query("SELECT * FROM ${Tables.REQUESTS_TABLE}")
    suspend fun getAllRequests(): List<RequestsEntity>
}
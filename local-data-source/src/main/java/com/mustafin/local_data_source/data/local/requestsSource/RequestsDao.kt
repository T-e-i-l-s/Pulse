package com.mustafin.local_data_source.data.local.requestsSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafin.local_data_source.data.local.db_core.Tables

/* Dao-interface of requests table */
@Dao
interface RequestsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: RequestsEntity)

    @Query("SELECT * FROM ${Tables.REQUESTS_TABLE} ORDER BY id DESC")
    suspend fun getAllRequests(): List<RequestsEntity>

    @Query("DELETE FROM ${Tables.REQUESTS_TABLE} WHERE id = :id")
    suspend fun deleteRequestById(id: Int)
}
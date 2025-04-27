package com.mustafin.local_data_source.data.local.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mustafin.local_data_source.data.local.db.Tables

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE ${Tables.REQUESTS_TABLE} ADD COLUMN responseStatuses TEXT")
        db.execSQL("""UPDATE ${Tables.REQUESTS_TABLE} SET responseStatuses = '[]'""")
        db.execSQL("""UPDATE ${Tables.REQUESTS_TABLE} SET lastResponseStatus = NULL""")
    }
}

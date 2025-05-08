package com.mustafin.local_data_source.data.local.db_core.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mustafin.local_data_source.data.local.db_core.Tables

/*
Migration from V1 to V2.
What has changed:
lastResponseStatus: HttpResponseStatusModel? -> responseStatuses : List<HttpResponseStatusModel?>
The new structure helps to save all server statuses instead of the latest one and display their timeline.
*/

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            ALTER TABLE ${Tables.REQUESTS_TABLE} 
            ADD COLUMN responseStatuses TEXT NOT NULL DEFAULT '[]'
            """
        )

        db.execSQL(
            """
            UPDATE ${Tables.REQUESTS_TABLE} 
            SET responseStatuses = '[]'
            """
        )

        db.execSQL(
            """
            CREATE TABLE ${Tables.REQUESTS_TABLE}_new (
                id INTEGER NOT NULL PRIMARY KEY,
                title TEXT NOT NULL DEFAULT 'undefined',
                description TEXT NOT NULL DEFAULT 'undefined',
                httpRequestInfo TEXT NOT NULL DEFAULT 'undefined',
                responseStatuses TEXT NOT NULL DEFAULT '[]',
                notificationsEnabled INTEGER NOT NULL DEFAULT 0
            )
            """
        )

        db.execSQL(
            """
            INSERT INTO ${Tables.REQUESTS_TABLE}_new (id, title, description, httpRequestInfo, responseStatuses, notificationsEnabled)
            SELECT id, title, description, httpRequestInfo, responseStatuses, notificationsEnabled
            FROM ${Tables.REQUESTS_TABLE}
            """
        )

        db.execSQL("DROP TABLE ${Tables.REQUESTS_TABLE}")

        db.execSQL(
            """
            ALTER TABLE ${Tables.REQUESTS_TABLE}_new 
            RENAME TO ${Tables.REQUESTS_TABLE}
            """
        )
    }
}

package com.nestor.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nestor.data.database.dao.TodoDao
import com.nestor.data.model.Todo

@Database(
    entities = [Todo::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

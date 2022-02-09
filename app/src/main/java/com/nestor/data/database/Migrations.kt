package com.nestor.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `TodoTmp` (`id` INTEGER PRIMARY KEY NOT NULL, `name` TEXT NOT NULL, `isMarked` INTEGER NOT NULL)")
            database.execSQL("INSERT INTO `TodoTmp`(`id`, `name`, `isMarked`) SELECT `id`, `name`, `isMarked` FROM `Todo`")
            database.execSQL("DROP TABLE `Todo`")
            database.execSQL("CREATE TABLE IF NOT EXISTS `Todo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `isMarked` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)")
            database.execSQL("INSERT INTO `Todo`(`name`, `isMarked`, `createdAt`) SELECT `name`, `isMarked`, ${Date().time} FROM `TodoTmp`")
            database.execSQL("DROP TABLE `TodoTmp`")
        }
    }
}
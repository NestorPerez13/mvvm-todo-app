package com.nestor.di

import android.content.Context
import androidx.room.Room
import com.nestor.data.database.Migrations
import com.nestor.data.database.TodosDatabase
import com.nestor.data.database.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesTodosDatabase(@ApplicationContext context: Context): TodosDatabase {
        return Room
            .databaseBuilder(context, TodosDatabase::class.java, "todos-db")
            .addMigrations(Migrations.MIGRATION_1_2)
            .build()
    }

    @Provides
    fun providesTodoDao(database: TodosDatabase): TodoDao {
        return database.todoDao()
    }
}
package com.nestor.di

import com.nestor.data.repository.TodoRepository
import com.nestor.data.repository.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsTodoRepository(todoRepositoryImpl: TodoRepositoryImpl): TodoRepository
}
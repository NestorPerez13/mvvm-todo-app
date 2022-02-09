package com.nestor.data.repository

import com.nestor.data.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAll(): Flow<List<Todo>>
    suspend fun insertTodo(todo: Todo): Long
    suspend fun deleteTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
}

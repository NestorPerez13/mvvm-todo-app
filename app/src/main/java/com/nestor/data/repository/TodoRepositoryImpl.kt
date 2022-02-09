package com.nestor.data.repository

import android.util.Log
import com.nestor.data.database.dao.TodoDao
import com.nestor.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "TodoRepositoryImpl"

class TodoRepositoryImpl @Inject constructor(private val todoDao: TodoDao) : TodoRepository {
    init {
        Log.i(TAG, "instance: ${this}")
    }

    override fun getAll(): Flow<List<Todo>> = todoDao.getAll()

    override suspend fun insertTodo(todo: Todo): Long {
        return withContext(Dispatchers.IO) {
            return@withContext todoDao.insertTodo(todo)
        }
    }

    override suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)

    override suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo)
}
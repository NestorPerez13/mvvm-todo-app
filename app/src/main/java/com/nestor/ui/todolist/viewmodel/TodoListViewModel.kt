package com.nestor.ui.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nestor.data.model.Todo
import com.nestor.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


private const val TAG = "TodoListViewModel"

@HiltViewModel
class TodoListViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {
    val mTodos: StateFlow<List<Todo>> = todoRepository.getAll()
        .flowOn(Dispatchers.IO)
        .onEach { list -> Log.i(TAG, "TodoListViewModel: ${list.size}") }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    init {
        Log.i(TAG, "init: ")
    }

    fun onFavoriteToggle(checked: Boolean, item: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(item.copy(isMarked = checked))
        }
    }
}

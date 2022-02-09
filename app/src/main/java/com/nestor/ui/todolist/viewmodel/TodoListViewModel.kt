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
    private val _onItemDeleted = MutableStateFlow<Todo?>(null)
    val onItemDeleted: StateFlow<Todo?> = _onItemDeleted

    fun onFavoriteToggle(checked: Boolean, item: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(item.copy(isMarked = checked))
        }
    }

    fun onDeleteItem(item: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(item)
            _onItemDeleted.emit(item)
        }
    }

    fun onUndoDelete(item: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insertTodo(item.copy(id = 0))
        }
    }

    fun onSnackBarConsumed() {
        _onItemDeleted.tryEmit(null)
    }
}

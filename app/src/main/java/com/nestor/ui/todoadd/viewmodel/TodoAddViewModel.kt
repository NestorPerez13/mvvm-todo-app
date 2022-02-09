package com.nestor.ui.todoadd.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nestor.data.database.dao.TodoDao
import com.nestor.data.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel @Inject constructor(private val todoDao: TodoDao) : ViewModel() {
    var todoName: String = ""
    private val navBack = MutableLiveData<Unit>()

    fun getNavBack(): LiveData<Unit> = navBack

    fun onSaveClick() {
        if (todoName.isEmpty()) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.insertTodo(Todo(name = todoName))
            navBack.postValue(Unit)
        }
    }
}

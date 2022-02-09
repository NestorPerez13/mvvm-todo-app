package com.nestor.ui.todolist.adapter

import com.nestor.data.model.Todo

interface OnDeleteTodo {
    fun onDeleteClick(todo: Todo)
}

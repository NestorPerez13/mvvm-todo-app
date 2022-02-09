package com.nestor.ui.todolist.adapter

import com.nestor.data.model.Todo

interface OnCheckedChanged {
    fun onCheckedChanged(checked: Boolean, item: Todo)
}

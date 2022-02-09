package com.nestor.ui.todolist.adapter

import android.text.SpannableStringBuilder
import android.widget.CheckBox
import androidx.core.text.italic
import androidx.core.text.strikeThrough
import androidx.databinding.BindingAdapter
import com.nestor.data.model.Todo

@BindingAdapter("strikeTextIfMarked")
fun strikeTextIfMarkedAdapter(checkBox: CheckBox, todo: Todo) {
    if (todo.isMarked) {
        checkBox.text = SpannableStringBuilder().strikeThrough { italic { append(todo.name) } }
    } else {
        checkBox.text = todo.name
    }
}

package com.nestor.ui.todolist.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

val sdf = SimpleDateFormat("EEE, MMM d HH:mm", Locale.getDefault())

@BindingAdapter("date")
fun dateAdapter(textView: TextView, date: Date) {
    textView.text = sdf.format(date)
}
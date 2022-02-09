package com.nestor.ui.base.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("if")
fun ifAdapter(view: View, condition: Boolean) {
    view.visibility = if (condition) View.VISIBLE else View.GONE
}

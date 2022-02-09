package com.nestor.ui.base.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("layoutManager")
fun setLayoutManager(
    recyclerView: RecyclerView,
    manager: RecyclerView.LayoutManager
) {
    recyclerView.layoutManager = manager
}
package com.nestor.ui.base.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


private const val TAG = "SetRecyclerViewAdapter"

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {
    recyclerView.adapter = adapter
}
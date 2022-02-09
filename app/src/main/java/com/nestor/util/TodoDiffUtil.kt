package com.nestor.util

import androidx.recyclerview.widget.DiffUtil
import com.nestor.data.model.Todo

class TodoDiffUtil(val oldList: List<Todo>, val newList: List<Todo>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].contentHashCode() == newList[newItemPosition].contentHashCode()
    }
}
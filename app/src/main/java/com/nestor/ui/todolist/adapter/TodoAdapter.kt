package com.nestor.ui.todolist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nestor.data.model.Todo
import com.nestor.todotasks.databinding.TodoItemViewBinding
import com.nestor.util.TodoDiffUtil

private const val TAG = "TodoAdapter"

class TodoAdapter(var items: List<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(),
    OnCheckedChanged, OnDeleteTodo {
    var checkedChanged: OnCheckedChanged? = null
    var deleteListener: OnDeleteTodo? = null

    inner class ViewHolder(val binding: TodoItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder =
        ViewHolder(
            binding = TodoItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            todo = items[position]
            checkedChanged = this@TodoAdapter
            deleteListener = this@TodoAdapter
        }
    }

    override fun getItemCount() = items.size

    fun updateItemsList(newItems: List<Todo>) {
        val diff = TodoDiffUtil(oldList = items, newList = newItems)
        items = newItems
        DiffUtil.calculateDiff(diff).dispatchUpdatesTo(this)
    }

    override fun onCheckedChanged(checked: Boolean, item: Todo) {
        checkedChanged?.onCheckedChanged(checked, item)
    }

    override fun onDeleteClick(todo: Todo) {
        deleteListener?.onDeleteClick(todo)
    }
}
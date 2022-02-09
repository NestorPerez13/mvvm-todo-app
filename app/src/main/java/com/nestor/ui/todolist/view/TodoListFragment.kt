package com.nestor.ui.todolist.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nestor.data.model.Todo
import com.nestor.todotasks.R
import com.nestor.todotasks.databinding.FragmentTodoListBinding
import com.nestor.ui.todolist.adapter.OnAddTodo
import com.nestor.ui.todolist.adapter.OnCheckedChanged
import com.nestor.ui.todolist.adapter.OnDeleteTodo
import com.nestor.ui.todolist.adapter.TodoAdapter
import com.nestor.ui.todolist.viewmodel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "TodoListFragment"

@AndroidEntryPoint
class TodoListFragment : Fragment(), OnAddTodo, OnDeleteTodo, OnCheckedChanged {
    private val todoListViewModel: TodoListViewModel by viewModels()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mBinding: FragmentTodoListBinding
    private lateinit var mAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mLayoutManager = LinearLayoutManager(requireContext())
        mAdapter =
            TodoAdapter(todoListViewModel.mTodos.value).apply {
                checkedChanged = this@TodoListFragment
                deleteListener = this@TodoListFragment
            }
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo_list, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.apply {
            viewModel = todoListViewModel
            adapter = mAdapter
            onAddTodo = this@TodoListFragment
            layoutManager = mLayoutManager
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                todoListViewModel.mTodos.collect {
                    mAdapter.updateItemsList(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                todoListViewModel.onItemDeleted.collect {
                    it?.let { todo ->
                        val snack =
                            Snackbar.make(mBinding.root, "Item deleted", Snackbar.LENGTH_SHORT)
                        snack.setAction("Undo") { todoListViewModel.onUndoDelete(todo) }
                        snack.show()
                    }
                }
            }
        }
    }

    override fun onAddTodoClick() {
        findNavController().navigate(TodoListFragmentDirections.actionTodoListFragmentToTodoAddFragment())
    }

    override fun onCheckedChanged(checked: Boolean, item: Todo) {
        todoListViewModel.onFavoriteToggle(checked, item)
    }

    override fun onDeleteClick(todo: Todo) {
        todoListViewModel.onDeleteItem(todo)
    }
}
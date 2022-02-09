package com.nestor.ui.todoadd.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nestor.todotasks.R
import com.nestor.todotasks.databinding.FragmentTodoAddBinding
import com.nestor.ui.todoadd.viewmodel.TodoAddViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoAddFragment : Fragment() {
    private val todoAddViewModel: TodoAddViewModel by viewModels()
    private lateinit var mBinding: FragmentTodoAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            FragmentTodoAddBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        mBinding.apply {
            viewModel = todoAddViewModel
        }
        todoAddViewModel.getNavBack().observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
        return mBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuSave) {
            todoAddViewModel.onSaveClick()
        }
        return super.onOptionsItemSelected(item)
    }
}
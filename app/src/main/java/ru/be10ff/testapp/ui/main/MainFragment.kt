package ru.be10ff.testapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import ru.be10ff.testapp.R
import ru.be10ff.testapp.TestApp
import ru.be10ff.testapp.domain.model.Item
import ru.be10ff.testapp.ui.main.views.Adapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory((requireActivity().application as TestApp).repository)
}

    val adapter = Adapter(){ bitmap ->
        activity?.supportFragmentManager?.beginTransaction()
            ?.let {
                it.replace(R.id.container, FullScreenFragment.newInstance(bitmap))
                it.addToBackStack(null)
                it.commit()
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserve()
        setupGUI()
    }

    private fun setupObserve() {
        viewModel.itemsState
            .distinctUntilChanged()
            .observe(viewLifecycleOwner){ items ->
            adapter.data.clear()
            adapter.data.addAll(items)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupGUI() {
        rvList.layoutManager = LinearLayoutManager(context)
        rvList.adapter = adapter

        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rvList)

        btnAdd.setOnClickListener {

            val input = EditText(requireContext())
                .apply {
                    setText(adapter.data.lastOrNull()?.source)
                }
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.dialog_caption)
                .setView(input)
                .setPositiveButton(R.string.dialog_ok){_, _ ->
                    viewModel.insert(Item(source = input.text.toString()))
                }
                .setNegativeButton(R.string.dialog_cancel){_, _ ->

                }
            .show()
        }
    }


    val callback = object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.dismiss(viewHolder.adapterPosition)
                ?.let{
                    viewModel.remove(it)
                }
        }

    }

}
package com.nemochen.digitalpasswallet.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nemochen.digitalpasswallet.databinding.MainFragmentBinding
import com.nemochen.digitalpasswallet.model.StreamPass

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var itemTouchHelper: ItemTouchHelper

    private val addPassDialogViewModel:AddPassDialogViewModel by activityViewModels()

    private var streamPassAdapter = StreamPassAdapter(object : ItemClick<StreamPass> {
        override fun onItemClicked(view: View, item: StreamPass) {
            PassDetailDialogFragment(item).show(parentFragmentManager, PassDetailDialogFragment.TAG)
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = streamPassAdapter

        itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                streamPassAdapter.activatePass(binding.recyclerView, viewModel.itemList.value, viewHolder.layoutPosition)
            }
        }).apply {
            this.attachToRecyclerView(binding.recyclerView)
        }

        binding.addBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                AddPassDialogFragment().show(parentFragmentManager, AddPassDialogFragment.TAG)
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            list -> streamPassAdapter.bindRecyclerViewWithSteamPassList(binding.recyclerView, list)
            binding.viewModel?.updateSerialNumberSet()
        })

        addPassDialogViewModel.newStreamPass.observe(viewLifecycleOwner, Observer {
            if (!binding.viewModel?.serialNumberSet!!.contains(it.serialNumber)) {
                streamPassAdapter.addItem(binding.recyclerView, viewModel.itemList.value, viewModel.itemList.value?.size!!, it)
                binding.viewModel?.updateSerialNumberSet()
            }
        })
    }

}
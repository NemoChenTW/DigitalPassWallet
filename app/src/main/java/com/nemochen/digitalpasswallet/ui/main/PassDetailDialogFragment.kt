package com.nemochen.digitalpasswallet.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nemochen.digitalpasswallet.databinding.PassDetailDialogFragmentBinding
import com.nemochen.digitalpasswallet.model.StreamPass

class PassDetailDialogFragment(val streamPass: StreamPass) : DialogFragment() {
    companion object {
        const val TAG = "PassDetailDialogFragment"
    }

    private lateinit var binding: PassDetailDialogFragmentBinding
    private lateinit var viewModel: PassDetailDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PassDetailDialogFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, PassDetailDialogViewModelFactory(streamPass))[PassDetailDialogViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
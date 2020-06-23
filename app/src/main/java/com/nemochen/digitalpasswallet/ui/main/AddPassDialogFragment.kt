package com.nemochen.digitalpasswallet.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.nemochen.digitalpasswallet.databinding.AddPassDialogFragmentBinding

class AddPassDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "AddPassDialogFragment"
    }

    private lateinit var binding: AddPassDialogFragmentBinding
    private val viewModel: AddPassDialogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddPassDialogFragmentBinding.inflate(inflater, container, false)

        binding.btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val serialNumber = binding.serialNumber.text.toString()
                if (serialNumber.isNotEmpty()) {
                    viewModel.newPass(serialNumber)
                }

                this@AddPassDialogFragment.dismiss()
            }
        })

        binding.btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                this@AddPassDialogFragment.dismiss()
            }
        })

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

    }
}
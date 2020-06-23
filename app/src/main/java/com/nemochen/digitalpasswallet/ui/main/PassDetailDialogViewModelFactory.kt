package com.nemochen.digitalpasswallet.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nemochen.digitalpasswallet.model.StreamPass

class PassDetailDialogViewModelFactory(private val streamPass: StreamPass): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PassDetailDialogViewModel::class.java)) {
            return PassDetailDialogViewModel(streamPass) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}
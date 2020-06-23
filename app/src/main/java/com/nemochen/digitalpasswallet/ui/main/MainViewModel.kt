package com.nemochen.digitalpasswallet.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nemochen.digitalpasswallet.model.DataModel
import com.nemochen.digitalpasswallet.model.StreamPass

class MainViewModel : ViewModel() {

    private val dataModel = DataModel()

    val itemList: LiveData<MutableList<StreamPass>> = liveData {
        val list = dataModel.retrieveData() as MutableList<StreamPass>
        emit(list)
    }

}
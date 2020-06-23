package com.nemochen.digitalpasswallet.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemochen.digitalpasswallet.model.StreamPass
import com.nemochen.digitalpasswallet.util.TimeDisplayUtil

class PassDetailDialogViewModel(private val streamPass: StreamPass) : ViewModel() {

    var serialNumber = MutableLiveData<String>(streamPass.serialNumber)
    var insertionDateString = MutableLiveData<String>(TimeDisplayUtil.dataTimeFormatter.format(streamPass.insertionTime))
    var passStatusString = MutableLiveData<String>(getPassStatusString())

    fun getPassStatusString(): String {
        var statusString = ""
        if (streamPass.isActivated()) {
            statusString = "Activation at: ${TimeDisplayUtil.dataTimeFormatter.format(streamPass.activeTime)}"
            statusString += "\nExpiration at: ${TimeDisplayUtil.dataTimeFormatter.format(streamPass.expirationTime)}"
        } else {
            statusString = "InActive"
        }

        return statusString
    }

}
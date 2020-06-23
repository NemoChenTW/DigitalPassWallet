package com.nemochen.digitalpasswallet.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nemochen.digitalpasswallet.model.StreamPass
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class AddPassDialogViewModel: ViewModel() {

    val newStreamPass = MutableLiveData<StreamPass>()

    fun newPass(serialNumber: String) {
         newStreamPass.value = createRandomDurationPass(serialNumber)
    }

    private fun createRandomDurationPass(serialNumber: String): StreamPass {
        val randomValue = Random.nextLong(1, 23)
        val isDay = Random.nextBoolean()
        val unit = if (isDay) TimeUnit.DAYS else TimeUnit.HOURS
        val duration = unit.toMillis(randomValue)

        return StreamPass(serialNumber, duration)
    }

}
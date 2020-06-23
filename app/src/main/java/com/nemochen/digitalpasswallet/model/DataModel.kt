package com.nemochen.digitalpasswallet.model

import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class DataModel {
    var mutableList: MutableList<StreamPass> = mutableListOf()
    init {
        mutableList.add(StreamPass("1234567", TimeUnit.DAYS.toMillis(7)))
        mutableList.add(StreamPass("2345678", TimeUnit.DAYS.toMillis(5)))
        mutableList.add(StreamPass("abcdefg", TimeUnit.HOURS.toMillis(6)))

        mutableList.add(StreamPass("QQzxcca", TimeUnit.HOURS.toMillis(10)).apply {
            activeTime = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1)
            expirationTime = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(3)
        })
    }

    suspend fun retrieveData(): List<StreamPass>{
        delay(1500)
        return mutableList
    }
}
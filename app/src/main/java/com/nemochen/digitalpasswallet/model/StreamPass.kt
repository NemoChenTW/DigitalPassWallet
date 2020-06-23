package com.nemochen.digitalpasswallet.model

import com.nemochen.digitalpasswallet.util.TimeDisplayUtil

data class StreamPass(val serialNumber: String, val duration: Long) {
    var insertionTime: Long = Long.MIN_VALUE
    var activeTime: Long = Long.MIN_VALUE
    var expirationTime: Long = Long.MIN_VALUE

    var durationDisplayString = ""
    var statusDisplayString = ""

    fun activate() {
        if (!isActivated()) {
            activeTime = System.currentTimeMillis()
            expirationTime = activeTime + duration
        }
    }

    private fun isExpired(): Boolean {
        return isActivated() && expirationTime < System.currentTimeMillis()
    }

    fun isActivated(): Boolean {
        return activeTime != Long.MIN_VALUE
    }

    fun getStatusDisplayString(steamPass: StreamPass): String {
        if (!steamPass.isActivated()) {
            return "Swipe to active"
        }

        return if (steamPass.isExpired()) {
            "Expired"
        } else {
            "Expired at: " + TimeDisplayUtil.dataTimeFormatter.format(steamPass.expirationTime)
        }
    }

}
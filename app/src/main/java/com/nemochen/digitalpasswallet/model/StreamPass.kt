package com.nemochen.digitalpasswallet.model

data class StreamPass(val serialNumber: String, val duration: Long) {
    var insertionTime: Long = Long.MIN_VALUE
    var activeTime: Long = Long.MIN_VALUE
    var expirationTime: Long = Long.MIN_VALUE

    var durationDisplayString = ""
    var statusDisplayString = ""
}
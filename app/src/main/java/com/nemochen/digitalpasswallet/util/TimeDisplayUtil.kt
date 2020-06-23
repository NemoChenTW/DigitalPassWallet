package com.nemochen.digitalpasswallet.util

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class TimeDisplayUtil {
    companion object {
        val dataTimeFormatter = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        fun getDurationDisplayString(remainingMillisecond: Long): String {
            var rDays = TimeUnit.MILLISECONDS.toDays(remainingMillisecond)
            var rHours = TimeUnit.MILLISECONDS.toHours(remainingMillisecond % TimeUnit.DAYS.toMillis(1))

            var dayDisplay = getDisplayString(rDays, "Day")
            var hourDisplay = getDisplayString(rHours, "Hour")

            var displayString = dayDisplay

            if (hourDisplay.isNotEmpty()) {
                displayString = if (displayString.isNotEmpty()) {
                    "$displayString, $hourDisplay"
                } else {
                    hourDisplay
                }
            }

            return displayString
        }

        private fun getDisplayString(value: Long, unit: String): String {
            var resultString = ""
            if (value > 0L) {
                resultString = "$value $unit"

                if (value > 1L) {
                    resultString += "s"
                }
            }
            return resultString
        }
    }
}
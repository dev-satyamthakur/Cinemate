package com.satyamthakur.cinemate.utils

object TimeConvertorUtility {
    fun convertMinutesToTimeString(minutes: Int): String {
        if (minutes < 0) {
            throw IllegalArgumentException("Input minutes should be non-negative.")
        }

        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        // Format the time components to ensure they have two digits
        val formattedHours = String.format("%02d", hours)
        val formattedMinutes = String.format("%02d", remainingMinutes)

        // Return the formatted time string
        return "$formattedHours:$formattedMinutes"
    }
}
package com.mmag.triviatraining.utils

import java.text.DecimalFormat

fun Double.toStringFormatted(): String {
    val df = DecimalFormat("#.##")
    return df.format(this)
}
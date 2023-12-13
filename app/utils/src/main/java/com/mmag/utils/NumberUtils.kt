package com.mmag.utils

import java.text.DecimalFormat
import kotlin.random.Random

fun Double.toStringFormatted(): String {
    val df = DecimalFormat("#.##")
    return df.format(this)
}

fun getRandomGradientAngle(): Float {
    val min = 180
    val max = 270
    return (Random.nextInt(min, max + 1)).toFloat()
}
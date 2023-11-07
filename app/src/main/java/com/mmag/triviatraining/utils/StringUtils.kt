package com.mmag.triviatraining.utils

fun String.cleanText() = this.replace("&quot;", "\"").replace("&#039;", "'")
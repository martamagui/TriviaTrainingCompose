package com.mmag.utils

fun String.cleanText() = this.replace("&quot;", "\"").replace("&#039;", "'")
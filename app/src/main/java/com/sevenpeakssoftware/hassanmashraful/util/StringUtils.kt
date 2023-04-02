package com.sevenpeakssoftware.hassanmashraful.util

import java.util.regex.Pattern


fun containsLink(link: String): Boolean {
    val pattern = Pattern.compile(URL_REGEX)
    val matcher = pattern.matcher(link)

    return matcher.find()
}
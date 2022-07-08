package com.hegunhee.routiner.util

fun String.removebracket() : String{
    return replace('[', ' ').replace(']',' ').trim()
}
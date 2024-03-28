package com.example.domain.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

data class Date(val date : Int) {

    val year : String = getLocalDate(date.toString()).year.toString()
    val month : String = getLocalDate(date.toString()).monthValue.toString()
    val dayOfMonth : String = getLocalDate(date.toString()).dayOfMonth.toString()
    val dayOfWeek : String = getLocalDate(date.toString()).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val desc : String = "${year}년 ${month}월 $dayOfMonth"
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        
        private fun getLocalDate(date : String) : LocalDate {
            return LocalDate.parse(date, formatter)
        }
    }
}
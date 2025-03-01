package com.example.domain.repository

import com.example.domain.model.Date

interface DateRepository {

    suspend fun insertDate(date : Int)

    suspend fun getDateList() : List<Date>

}
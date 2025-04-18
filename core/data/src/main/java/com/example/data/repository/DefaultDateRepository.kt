package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.mapper.toDateEntity
import com.example.data.mapper.toDateList
import hegunhee.routiner.model.Date
import com.example.domain.repository.DateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultDateRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : DateRepository {

    override suspend fun insertDate(date: Int) {
        return localDataSource.insertDate(Date(date).toDateEntity())
    }

    override suspend fun getDateList(): List<Date> {
        return localDataSource.getDateList().toDateList()
    }

    override suspend fun getRoutineExistDateList(): List<Date> {
        return localDataSource.getRoutineExistDateList().toDateList()
    }

}


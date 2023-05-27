package com.example.data.dataSource.local

import javax.inject.Inject

@Singleton
class DefaultLocalDataSource @Inject constructor(
    private val routineDao: RoutineDao,
    private val dateDao: DateDao,
    private val reviewDao: ReviewDao,
    private val repeatRoutineDao: RepeatRoutineDao,
    private val categoryDao: CategoryDao,
    private val sharedPreferenceManager: SharedPreferenceManager
) : LocalDataSource {
}
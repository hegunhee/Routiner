package routiner.core.domain.repository

import hegunhee.routiner.model.Date

interface DateRepository {

    suspend fun insertDate(date : Int)

    suspend fun getDateList() : List<Date>

    suspend fun getRoutineExistDateList() : List<Date>

}
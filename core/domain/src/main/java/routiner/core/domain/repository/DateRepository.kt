package routiner.core.domain.repository

import routiner.core.model.Date

interface DateRepository {

    suspend fun insertDate(date : Int)

    suspend fun getDateList() : List<Date>

    suspend fun getRoutineExistDateList() : List<Date>

}
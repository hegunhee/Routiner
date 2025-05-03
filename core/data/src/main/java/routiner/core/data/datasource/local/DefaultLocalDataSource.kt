package routiner.core.data.datasource.local

import routiner.core.data.db.SharedPreferenceManager
import kotlinx.coroutines.flow.Flow
import routiner.core.data.db.dao.CategoryDao
import routiner.core.data.db.dao.DateDao
import routiner.core.data.db.dao.RepeatRoutineDao
import routiner.core.data.db.dao.ReviewDao
import routiner.core.data.db.dao.RoutineDao
import routiner.core.data.entity.CategoryEntity
import routiner.core.data.entity.DateEntity
import routiner.core.data.entity.RepeatRoutineEntity
import routiner.core.data.entity.ReviewEntity
import routiner.core.data.entity.RoutineEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultLocalDataSource @Inject constructor(
    private val routineDao: RoutineDao,
    private val dateDao: DateDao,
    private val reviewDao: ReviewDao,
    private val repeatRoutineDao: RepeatRoutineDao,
    private val categoryDao: CategoryDao,
    private val sharedPreferenceManager: SharedPreferenceManager
) : LocalDataSource {

    override suspend fun insertRoutine(routineEntity: RoutineEntity) {
        routineDao.insertRoutine(routineEntity)
    }

    override suspend fun insertRoutines(routineList: List<RoutineEntity>): List<Long> {
        return routineDao.insertRoutines(routineList)
    }

    override fun getRoutinesFlowByDate(date: Int): Flow<List<RoutineEntity>> {
        return routineDao.getRoutinesFlowByDate(date)
    }

    override suspend fun getRoutinesByDate(date: Int): List<RoutineEntity> {
        return routineDao.getRoutinesByDate(date)
    }

    override suspend fun deleteRoutine(id: Int): Int {
        return routineDao.deleteRoutine(id)
    }

    override suspend fun deleteRoutinesByDate(date: Int): Int {
        return routineDao.deleteRoutinesByDate(date)
    }

    override suspend fun updateRoutine(routineEntity: RoutineEntity) {
        routineDao.updateRoutine(routineEntity)
    }


    override suspend fun insertDate(date: DateEntity) {
        return dateDao.insertDate(date)
    }

    override suspend fun getDateList(): List<DateEntity> {
        return dateDao.getDateList()
    }

    override suspend fun getRoutineExistDateList(): List<DateEntity> {
        return routineDao.getDistinctDateList().map { DateEntity(it) }
    }


    override suspend fun insertReview(review: ReviewEntity) {
        reviewDao.insertReview(review)
    }

    override suspend fun getReviewOrNullByDate(date: Int): ReviewEntity? {
        return reviewDao.getReviewOrNullByDate(date)
    }

    override suspend fun deleteReview(review: ReviewEntity): Int {
        return reviewDao.deleteReview(review)
    }


    override suspend fun insertRepeatRoutine(repeatRoutine: RepeatRoutineEntity) {
        repeatRoutineDao.insertRepeatRoutine(repeatRoutine)
    }

    override fun getRepeatRoutinesFlow(): Flow<List<RepeatRoutineEntity>> {
        return repeatRoutineDao.getRepeatRoutinesFlow()
    }

    override suspend fun getRepeatRoutines(): List<RepeatRoutineEntity> {
        return repeatRoutineDao.getRepeatRoutines()
    }

    override suspend fun deleteRepeatRoutine(text: String): Int {
        return repeatRoutineDao.deleteRepeatRoutine(text)
    }


    override suspend fun insertCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    override fun getCategoriesFlow(): Flow<List<CategoryEntity>> {
        return categoryDao.getCategoriesFlow()
    }

    override suspend fun deleteCategory(categoryEntity: CategoryEntity): Int {
        return categoryDao.deleteCategory(categoryEntity)
    }


    override suspend fun getCurrentDate(): Int {
        return sharedPreferenceManager.getCurrentDate()
    }

    override suspend fun setCurrentDate(date: Int) {
        sharedPreferenceManager.setCurrentDate(date)
    }

    override fun setAlarmNotiTime(time: String) {
        sharedPreferenceManager.setAlarmNotiTime(time)
    }

    override fun getAlarmNotiTime(): String {
        return sharedPreferenceManager.getAlarmNotiTime()
    }

    override fun isAppFirstOpened(): Boolean {
        return sharedPreferenceManager.isAppFirstOpened()
    }

    override fun setAppFirstOpened() {
        return sharedPreferenceManager.setAppFirstOpened()
    }

}

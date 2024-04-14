package com.example.data.mapper

import com.example.data.entity.CategoryEntity
import com.example.data.entity.*
import com.example.domain.model.*
import com.hegunhee.routiner.util.*

fun Category.toCategoryEntity() : CategoryEntity =
    CategoryEntity(name)

fun Date.toDateEntity() : DateEntity =
    DateEntity(date)

fun RepeatRoutine.toRepeatRoutineEntity() : RepeatRoutineEntity =
    RepeatRoutineEntity(text, dayOfWeekList, category)

fun Review.toReviewEntity() : ReviewEntity =
    ReviewEntity(date, review)

fun Routine.toRoutineEntity() : RoutineEntity =
    RoutineEntity(date, text, isFinished, category, id)

fun List<RoutineEntity>.toRoutineList() : List<Routine> =
    this.map { Routine(it.date,it.text,it.isFinished,it.category,it.id) }

fun List<DateEntity>.toDateList() : List<Date> =
    this.map { Date(it.date) }

fun ReviewEntity?.toReviewOrNull() : Review? {
    return if(this == null){
        null
    }else{
        Review(date,review)
    }
}

internal fun String.toNotiAlarm() : AlarmTime {
    return if(this.contains(":")) {
        val (hour, minute) = this.split(":")
        AlarmTime(hour = hour,minute = minute)
    }else {
        AlarmTime.DEFAULT
    }
}

fun List<RepeatRoutineEntity>.toRepeatRoutineList() : List<RepeatRoutine> =
    this.map { RepeatRoutine(it.text,it.dayOfWeekList,it.category) }

fun List<RepeatRoutineEntity>.toRoutineEntityList() : List<RoutineEntity> =
    this.map { RoutineEntity(date = getTodayDate(),text = it.text, category = it.category) }

fun List<Routine>.toRoutineEntity() : List<RoutineEntity> =
    this.map { it.toRoutineEntity() }

fun List<CategoryEntity>.toCategory() : List<Category> =
    this.map { Category(it.name) }

fun List<DayOfWeekEntity>.toDayOfWeekList() : List<DayOfWeek> =
    this.map {DayOfWeek(it.date,it.isSelected)}
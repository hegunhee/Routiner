package com.example.data.db.dao

import com.example.data.entity.CategoryEntity
import com.example.data.entity.*
import com.example.domain.model.*

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

fun List<ReviewEntity>.toReviewList() : List<Review> =
    this.map { Review(it.date,it.review) }

fun List<RepeatRoutineEntity>.toRepeatRoutineList() : List<RepeatRoutine> =
    this.map { RepeatRoutine(it.text,it.dayOfWeekList,it.category) }

fun List<Routine>.toRoutineEntity() : List<RoutineEntity> =
    this.map { it.toRoutineEntity() }

fun List<CategoryEntity>.toCategory() : List<Category> =
    this.map { Category(it.name) }
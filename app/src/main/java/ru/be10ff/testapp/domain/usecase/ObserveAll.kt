package ru.be10ff.testapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.domain.model.Item

interface ObserveAll {
    fun getAll(): Flow<List<Item>>
}
package ru.be10ff.testapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.domain.model.Item

interface ItemsRepository {
    fun getAll() : Flow<List<Item>>
    suspend fun insert(item: Item): Long
    suspend fun remove(item: Item)
}
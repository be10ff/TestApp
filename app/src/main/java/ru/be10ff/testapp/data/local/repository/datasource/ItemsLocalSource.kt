package ru.be10ff.testapp.data.local.repository.datasource

import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.domain.model.Item

interface ItemsLocalSource {
    fun getAll() : Flow<List<Item>>
    suspend fun insert(item: Item) : Long
    suspend fun remove(item: Item)
}
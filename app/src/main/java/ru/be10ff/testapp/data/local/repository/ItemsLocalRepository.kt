package ru.be10ff.testapp.data.local.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.data.local.ItemDao
import ru.be10ff.testapp.data.local.repository.datasource.ItemsLocalSource
import ru.be10ff.testapp.data.local.repository.datasource.ItemsLocalSourceImpl
import ru.be10ff.testapp.domain.model.Item
import ru.be10ff.testapp.domain.repository.ItemsRepository

class ItemsLocalRepository(dao: ItemDao) : ItemsRepository {
    val localSource: ItemsLocalSource = ItemsLocalSourceImpl(dao)

    override fun getAll() : Flow<List<Item>> = localSource.getAll()

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    override suspend fun insert(item: Item): Long = localSource.insert(item)

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    override suspend fun remove(item: Item) = localSource.remove(item)
}
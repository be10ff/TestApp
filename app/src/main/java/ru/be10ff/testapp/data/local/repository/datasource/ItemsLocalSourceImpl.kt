package ru.be10ff.testapp.data.local.repository.datasource

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.be10ff.testapp.data.local.ItemDao
import ru.be10ff.testapp.data.local.repository.ItemEntityMapper
import ru.be10ff.testapp.domain.model.Item

class ItemsLocalSourceImpl (
    private val dao: ItemDao
) : ItemsLocalSource {
    private val mapper = ItemEntityMapper()

    override fun getAll() : Flow<List<Item>> {
        return dao.getAll()
            .distinctUntilChanged()
            .map { items ->
                items.map { mapper.mapTo(it) }
            }
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
   override suspend fun insert(item: Item) : Long {
        return dao.insert(mapper.mapTo(item))
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    override suspend fun remove(item: Item) {
        return dao.remove(mapper.mapTo(item))
    }
}
package ru.be10ff.testapp.domain.usecase

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.domain.model.Item

interface RemoveItem {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun remove(item: Item)
}
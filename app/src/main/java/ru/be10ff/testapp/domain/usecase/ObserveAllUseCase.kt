package ru.be10ff.testapp.domain.usecase

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.data.local.repository.ItemsLocalRepository
import ru.be10ff.testapp.domain.model.Item
import ru.be10ff.testapp.domain.repository.ItemsRepository

class ObserveAllUseCase(private val repository: ItemsRepository): ObserveAll {
    override fun getAll(): Flow<List<Item>>  {
        return repository.getAll()
    }
}
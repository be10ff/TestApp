package ru.be10ff.testapp.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.be10ff.testapp.data.remote.repository.RemoteRepositoryImpl
import ru.be10ff.testapp.domain.model.Item
import ru.be10ff.testapp.domain.repository.ItemsRepository
import ru.be10ff.testapp.domain.repository.RemoteRepository
import ru.be10ff.testapp.domain.usecase.*

class MainViewModel(private val repository: ItemsRepository) : ViewModel() {

    private val remoteRepository: RemoteRepository = RemoteRepositoryImpl()
    private val insertItemUseCase: InsertItem = InsertItemUseCase(repository)
    private val removeItemUseCase: RemoveItem = RemoveItemUseCase(repository)
    private val downloadPicture: DownloadPicture = DownloadPictureUseCase(remoteRepository)
    private val observeAllUseCase: ObserveAll = ObserveAllUseCase(repository)

    val itemsState: LiveData<List<Item>> = observeAllUseCase.getAll()
        .map { items ->
            items.map {
                viewModelScope.async (Dispatchers.IO) {
                    downloadPicture.download(it)
                }.await()
            }
        }
        .onEach {
            val res = it
        }
        .asLiveData()

    fun insert(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            insertItemUseCase.insert(item)
        }

    }

    fun remove(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            removeItemUseCase.remove(item)
        }
    }

    class Factory(private val repository: ItemsRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java))
                @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
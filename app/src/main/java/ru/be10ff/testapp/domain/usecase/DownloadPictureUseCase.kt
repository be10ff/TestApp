package ru.be10ff.testapp.domain.usecase

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.data.local.repository.ItemsLocalRepository
import ru.be10ff.testapp.domain.model.Item
import ru.be10ff.testapp.domain.repository.ItemsRepository
import ru.be10ff.testapp.domain.repository.RemoteRepository

class DownloadPictureUseCase(private val repository: RemoteRepository): DownloadPicture {
    override suspend fun download(item: Item) : Item = repository.downloadPicture(item)
}
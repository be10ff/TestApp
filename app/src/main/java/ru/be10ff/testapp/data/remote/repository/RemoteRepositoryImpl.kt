package ru.be10ff.testapp.data.remote.repository

import ru.be10ff.testapp.data.remote.repository.datasource.ImageRemoteSource
import ru.be10ff.testapp.data.remote.repository.datasource.ImageRemoteSourceImpl
import ru.be10ff.testapp.domain.model.Item
import ru.be10ff.testapp.domain.repository.RemoteRepository

class RemoteRepositoryImpl : RemoteRepository {
    val remoteSource: ImageRemoteSource = ImageRemoteSourceImpl()
    override suspend fun downloadPicture(item: Item): Item {
        return remoteSource.download(item)
    }
}
package ru.be10ff.testapp.data.remote.repository.datasource

import ru.be10ff.testapp.domain.model.Item

interface ImageRemoteSource {
    suspend fun download(item: Item): Item
}
package ru.be10ff.testapp.domain.repository

import ru.be10ff.testapp.domain.model.Item

interface RemoteRepository {
    suspend fun downloadPicture(item: Item): Item
}
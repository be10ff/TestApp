package ru.be10ff.testapp.data.local.repository

import ru.be10ff.testapp.data.local.entity.ItemDB
import ru.be10ff.testapp.domain.model.Item

class ItemEntityMapper {
    fun mapTo(from: ItemDB): Item {
        return with(from) {
            Item(
                roomId = roomId,
                source = source
            )
        }
    }

    fun mapTo(from: Item) : ItemDB {
        return with(from) {
            ItemDB(
                roomId = roomId,
                source = source
            )
        }
    }
}
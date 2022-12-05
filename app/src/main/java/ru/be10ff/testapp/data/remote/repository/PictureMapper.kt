package ru.be10ff.testapp.data.remote.repository

import ru.be10ff.testapp.data.remote.data.Picture
import ru.be10ff.testapp.domain.model.Item

class PictureMapper {
    fun mapTo(from: Picture): Item {
        return with(from) {
            Item(
                roomId = id,
                source = source,
                img = bitmap,
            )
        }
    }

    fun mapTo(from: Item) : Picture {
        return with(from) {
            Picture(
                id = roomId,
                source = source,
                bitmap = img
            )
        }
    }
}
package ru.be10ff.testapp.data.remote.repository.datasource

import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.be10ff.testapp.domain.model.Item
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageRemoteSourceImpl : ImageRemoteSource{
    override suspend fun download(item: Item): Item {
        return withContext(Dispatchers.IO){
try {
    val url = URL(item.source)
    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
    connection.connect()
    val input = BufferedInputStream(connection.inputStream)
    val img = BitmapFactory.decodeStream(input)
    item.copy(img = img)
    } catch(e: Exception) {
        item
    }
        }
    }
}
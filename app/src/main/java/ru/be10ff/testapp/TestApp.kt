package ru.be10ff.testapp

import android.app.Application
import androidx.room.Room
import ru.be10ff.testapp.data.local.LocalDatabase
import ru.be10ff.testapp.data.local.repository.ItemsLocalRepository
import ru.be10ff.testapp.domain.repository.ItemsRepository

class TestApp: Application() {

    private val db: LocalDatabase by lazy {
        Room.databaseBuilder(this, LocalDatabase::class.java, "data_base").build()
    }
    val repository: ItemsRepository by lazy { ItemsLocalRepository(db.itemDao()) }

    override fun onCreate() {
        super.onCreate()
    }

}
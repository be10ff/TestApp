package ru.be10ff.testapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.be10ff.testapp.data.local.ItemDao
import ru.be10ff.testapp.data.local.entity.ItemDB

@Database(
    entities = [ItemDB::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun itemDao() : ItemDao
}
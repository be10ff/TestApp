package ru.be10ff.testapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.be10ff.testapp.data.local.entity.ItemDB

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<ItemDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity : ItemDB) : Long

    @Delete
    fun remove(entity : ItemDB)
}
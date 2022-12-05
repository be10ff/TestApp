package ru.be10ff.testapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "items"
)
data class ItemDB (
    @PrimaryKey(autoGenerate = true) val roomId: Int,
    @ColumnInfo(name = "source") val source: String
    )
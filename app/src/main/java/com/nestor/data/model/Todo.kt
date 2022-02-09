package com.nestor.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val isMarked: Boolean = false,
    val createdAt: Date = Date()
) {
    fun contentHashCode() = "${name}${isMarked}"
}

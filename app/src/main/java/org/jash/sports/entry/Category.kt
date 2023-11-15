package org.jash.sports.entry

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Category(
    val createTime: Date,
    @PrimaryKey
    val id: Int,
    val info: String,
    val name: String
)
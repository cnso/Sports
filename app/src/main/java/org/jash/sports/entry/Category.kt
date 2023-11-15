package org.jash.sports.entry

import android.content.Context
import android.widget.Toast
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
) {
    fun click(context: Context) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }
}
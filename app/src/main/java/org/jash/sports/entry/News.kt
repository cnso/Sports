package org.jash.sports.entry

import java.util.Date

data class News(
    val content: String,
    val createTime: Date,
    val flag: Int,
    val id: Int,
    val imgurl: String,
    val looks: Int,
    val ntid: Int,
    val suid: Int,
    val title: String
)
package org.jash.sports.entry

data class Res<T>(
    val code: Int,
    val msg: String,
    val data:T
)
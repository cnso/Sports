package org.jash.sports.entry

data class Page<T>(
    val countId: Int,
    val current: Int,
    val maxLimit: Int,
    val optimizeCountSql: Boolean,
    val orders: List<Any>,
    val pages: Int,
    val records: List<T>,
    val searchCount: Boolean,
    val size: Int,
    val total: Int
)
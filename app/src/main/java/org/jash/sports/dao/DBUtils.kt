package org.jash.sports.dao

import android.content.Context
import org.jash.common.utils.LoadDB

val Context.database:NewsDatabase by LoadDB(NewsDatabase::class.java, "news")
package org.jash.common.utils

import android.util.Log

val Any.TAG:String
    get() = javaClass.simpleName

fun Any.log(msg:Any?) {
//    if(BuildConfig.DEBUG){
        Log.d(TAG, msg.toString())
//    }
}
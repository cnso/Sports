package org.jash.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.drawee.backends.pipeline.Fresco
import org.jash.common.utils.token

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        Fresco.initialize(this)
        token = getSharedPreferences("login_info", MODE_PRIVATE)
            .getString("token", null)
    }
}
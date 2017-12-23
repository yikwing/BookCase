package com.rongyi.bookcase

import android.app.Application

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/12/20
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: MyApplication? = null
            private set
    }

}
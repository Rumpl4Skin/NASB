package by.ssrlab.NASB.app

import android.app.Application

class App : Application(){
    companion object {
        var INSTANSE : App? = null
    }

    override fun onCreate() {
        super.onCreate()
        INSTANSE = this
    }
}
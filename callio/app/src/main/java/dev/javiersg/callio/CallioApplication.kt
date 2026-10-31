package dev.javiersg.callio

import android.app.Application
import dev.javiersg.callio.core.di.CallioContainer

class CallioApplication : Application() {

    lateinit var container: CallioContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = CallioContainer(applicationContext)
    }
}
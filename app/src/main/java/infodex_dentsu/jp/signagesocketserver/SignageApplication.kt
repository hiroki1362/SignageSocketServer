package infodex_dentsu.jp.signagesocketserver

import android.app.Application
import timber.log.Timber

class SignageApplication: Application() {
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}
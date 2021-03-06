package dev.forcetower.instascan

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk.getApplicationSignature
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class InstrackApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // "Every time you log in production, a puppy dies"
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.d(getApplicationSignature(this))
    }
}
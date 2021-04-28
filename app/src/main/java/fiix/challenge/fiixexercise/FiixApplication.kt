package fiix.challenge.fiixexercise

import android.app.Application
import fiix.challenge.fiixexercise.kotlinsample.di.fiixAppModule
import fiix.challenge.fiixexercise.kotlinsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class FiixApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FiixApplication)
            modules(listOf(fiixAppModule, viewModelModule))
        }
    }
}
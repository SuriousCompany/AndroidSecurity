package company.surious.security.core

import android.app.Application
import company.surious.data.dataModule
import company.surious.domain.domainModule
import company.surious.security.view_models.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(dataModule, domainModule, viewModelModule)
        }
    }
}
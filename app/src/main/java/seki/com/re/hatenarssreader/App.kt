package seki.com.re.hatenarssreader

import android.app.Application
import seki.com.re.hatenarssreader.di.ApplicationComponent
import seki.com.re.hatenarssreader.di.DaggerApplicationComponent

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .context(this)
            .build()
    }
}
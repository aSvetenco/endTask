package com.sa.endtask

import com.sa.endtask.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> = DaggerAppComponent.builder().create(this)
}
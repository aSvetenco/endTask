package com.sa.endtask.di

import com.sa.endtask.App
import com.sa.endtask.di.module.ActivityModule
import com.sa.endtask.di.module.AppModule
import com.sa.endtask.di.module.NetworkModule
import com.sa.endtask.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        NetworkModule::class]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
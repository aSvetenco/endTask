package com.sa.endtask.di.module

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import com.sa.endtask.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    internal fun context(app: App): Context = app.applicationContext

    @Provides
    internal fun resources(context: Context): Resources = context.resources

}
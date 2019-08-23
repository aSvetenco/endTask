package com.sa.endtask.di.module

import com.insomn.courts.app.di.ActivityScoped
import com.sa.endtask.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

}
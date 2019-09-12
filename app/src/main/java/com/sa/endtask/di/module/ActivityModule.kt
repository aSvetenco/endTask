package com.sa.endtask.di.module

import com.sa.endtask.di.ActivityScoped
import com.sa.endtask.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

}
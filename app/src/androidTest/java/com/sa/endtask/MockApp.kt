package com.sa.endtask

import com.sa.endtask.di.DaggerMockAppComponent
import dagger.android.AndroidInjector

class MockApp : App() {
    override fun applicationInjector(): AndroidInjector<App> = DaggerMockAppComponent.builder().create(this)
}
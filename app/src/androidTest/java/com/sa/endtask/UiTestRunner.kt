package com.sa.endtask

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class UiTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, MockApp::class.java.name, context)
    }
}

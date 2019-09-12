package com.sa.endtask.di.module

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.sa.endtask.api.MockProductClient
import com.sa.endtask.api.client.ProductClientContract
import com.sa.endtask.di.InstrumentationContext
import com.sa.endtask.server.AssetsParser
import com.sa.endtask.server.MockServer
import com.sa.endtask.server.MockServerImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockClientModule {

    @Provides
    fun assertParser(@InstrumentationContext context: Context): AssetsParser {
        return AssetsParser(context.assets)
    }

    @Provides
    @Singleton
    @InstrumentationContext
    fun instrumentationContext(): Context {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        return instrumentation.context
    }

    @Provides
    fun providesServer(assetsParser: AssetsParser): MockServer = MockServerImp(assetsParser)

    @Provides
    fun productClient(mockServer: MockServer): ProductClientContract = MockProductClient(mockServer)
}
package com.sa.endtask.di.module

import com.sa.endtask.api.Api
import com.sa.endtask.api.client.ProductClient
import com.sa.endtask.api.client.ProductClientContract
import dagger.Module
import dagger.Provides

@Module
class ClientModule {

    @Provides
    fun productClient(api: Api): ProductClientContract = ProductClient(api)
}
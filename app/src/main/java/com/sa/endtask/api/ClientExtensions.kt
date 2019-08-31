package com.sa.endtask.api

import retrofit2.Call

internal fun <T> Call<T>.body(): T {
    val response = execute()

    if (response.isSuccessful) return response.body()!!
    else throw Throwable(response.errorBody()?.string())
}
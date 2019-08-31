package com.sa.endtask.server

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException

class AssetsParser(private val assetManager: AssetManager) {

    @Throws(IOException::class)
    fun getRawResponse(path: String) =
        assetManager.open(path).bufferedReader().use { it.readText() }

    @Throws(JsonSyntaxException::class)
    fun <T> getResponse(path: String, response: Class<T>): T =
        Gson().fromJson(getRawResponse(path), response)
}
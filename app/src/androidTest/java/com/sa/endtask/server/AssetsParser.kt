package com.sa.endtask.server

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class AssetsParser(private val assetManager: AssetManager) {

    @Throws(IOException::class)
    fun getRawResponse(path: String): String {
        val inputStream = assetManager.open(path)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val result = reader.readText()
        reader.close()
        return result
    }

    @Throws(JsonSyntaxException::class)
    fun <T> getResponse(path: String, response: Class<T>): T = Gson().fromJson(getRawResponse(path), response)


}
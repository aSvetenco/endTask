package com.sa.endtask.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sa.endtask.R
import com.sa.endtask.di.api.client.ProductClient
import com.sa.endtask.di.api.models.Product
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(private val client: ProductClient) : ViewModel(),
    CoroutineScope {

    private val job = Job()
    val productList = MutableLiveData<List<Product>>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Int>()

    override val coroutineContext = Dispatchers.Main + job

    fun loadProductList() {
        progress.value = true
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = client.getProductList().execute()

                    if (response.isSuccessful) {
                        val list = response.body()!!.products
                        productList.postValue(list)
                    } else throw Throwable(response.errorBody()?.string())

                }
            } catch (t: Throwable) {
                onError(t)
            } finally {
                progress.value = false
            }
        }
    }

    private fun onError(t: Throwable) {
        Log.d("Request Fails", t.message, t)
        error.postValue(R.string.error_general)
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}
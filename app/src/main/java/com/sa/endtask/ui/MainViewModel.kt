package com.sa.endtask.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sa.endtask.di.api.client.ProductClient
import com.sa.endtask.di.api.models.Product
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val client: ProductClient) : ViewModel() {


    private val disposable = CompositeDisposable()
    val productList = MutableLiveData<List<Product>>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Int>()

    fun loadProductList() {
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
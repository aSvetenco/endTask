package com.sa.endtask.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sa.endtask.R
import com.sa.endtask.di.api.client.ProductClient
import com.sa.endtask.di.api.models.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val client: ProductClient) : ViewModel() {


    private val disposable = CompositeDisposable()
    val productList = MutableLiveData<List<Product>>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Int>()

    fun loadProductList() {
        progress.value = true
        disposable.add(
            client.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.products }
                .doFinally { progress.value = false }
                .subscribe({ productList.value = it }, {
                    Log.d("FAIL", it.localizedMessage, it)
                    error.value = R.string.error_general
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
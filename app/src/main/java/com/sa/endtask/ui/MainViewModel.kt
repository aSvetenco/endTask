package com.sa.endtask.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sa.endtask.R
import com.sa.endtask.api.client.ProductClientContract
import com.sa.endtask.api.models.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val client: ProductClientContract) : ViewModel() {

    private val disposable = CompositeDisposable()
    val productList = MutableLiveData<List<Product>>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Int>()

    fun loadProductList() {
        progress.value = true
        disposable.add(
            client.getProductList()
                .subscribeOn(Schedulers.io())
                .map { it.products }
                .doFinally { progress.postValue(false)}
                .subscribe({ productList.postValue(it) }, {
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
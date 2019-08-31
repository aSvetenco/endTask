package com.sa.endtask.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sa.endtask.api.client.ProductClientContract
import com.sa.endtask.api.models.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.HttpException
import javax.inject.Inject

class MainViewModel @Inject constructor(private val client: ProductClientContract) : ViewModel() {

    private val disposable = CompositeDisposable()
    val progress: BehaviorSubject<Boolean> = BehaviorSubject.create()
    val error: BehaviorSubject<String> = BehaviorSubject.create()
    val productList: BehaviorSubject<List<Product>> = BehaviorSubject.create()

    fun loadProductList() {
        progress.onNext(true)
        disposable.add(
            client.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.products }
                .doFinally { progress.onNext(false) }
                .subscribe(::onListLoaded, ::onError)
        )
    }

    private fun onListLoaded(list: List<Product>) {
        productList.onNext(list)
    }

    private fun onError(t: Throwable) {
        Log.d("FAIL", t.localizedMessage, t)
        if (t.isHttp()) error.onNext(t.localizedMessage)
        else error.onNext("Something went wrong")
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun Throwable.isHttp() = this is HttpException
}
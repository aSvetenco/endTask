package com.sa.endtask.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sa.endtask.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val adapter = ProductListAdapter()

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        title = getString(R.string.product_list_title)
        list.adapter = adapter
        viewModel.productList.observe(this, Observer(adapter::submitList))
        viewModel.error.observe(this, Observer(::showMessage))
        viewModel.progress.observe(this, Observer { progress.isRefreshing = it })
        viewModel.loadProductList()
        progress.setOnRefreshListener { viewModel.loadProductList() }
    }

    override fun onCreateOptionsMenu(menu: Menu?) =
        if (menu != null) {
            menuInflater.inflate(R.menu.main_menu, menu)
            true
        } else super.onCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem?) =
        when (item?.itemId) {
            R.id.gridView -> {
                item.isChecked = !item.isChecked
                changeLayoutManager(ListType.GRID_VIEW)
                true
            }
            R.id.listView -> {
                item.isChecked = !item.isChecked
                changeLayoutManager(ListType.LIST_VIEW)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun changeLayoutManager(type: ListType) {
        var manager = list.layoutManager
        val position = (manager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        manager = if (type == ListType.LIST_VIEW) LinearLayoutManager(this)
        else GridLayoutManager(this, 2)
        list.layoutManager = manager
        list.scrollToPosition(position)
    }

    private fun showMessage(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private enum class ListType {
        LIST_VIEW,
        GRID_VIEW
    }
}

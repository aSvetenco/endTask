package com.sa.endtask.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sa.endtask.R
import com.sa.endtask.di.api.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product_list.*

class ProductListAdapter : ListAdapter<Product, ProductListAdapter.ProductListVH>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListVH =
        ProductListVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false))

    override fun onBindViewHolder(holder: ProductListVH, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductListVH(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Product) {
            title.text = item.name
            price.text = item.price
            productImage.load(item.image)
        }
    }


    private class ItemCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }
}

private fun ImageView.load(url: String) {
    Picasso.get()
        .load(if (url.trim().isEmpty()) ERROR_URL else url.trim())
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_placeholder)
        .into(this)
}

private const val ERROR_URL = "http://kek"
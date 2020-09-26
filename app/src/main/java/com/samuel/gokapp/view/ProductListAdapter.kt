package com.samuel.gokapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samuel.gokapp.R
import com.samuel.gokapp.model.gokrepository.ProductsModel
import com.samuel.gokapp.databinding.ItemProductBinding

class ProductListAdapter(private val productList: ArrayList<ProductsModel>) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemProductBinding>(
                inflater,
                R.layout.item_product,
                parent,
                false
            )
        return ProductListViewHolder(view)
    }

    override fun getItemCount() = productList.size


    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.view.product = productList[position]
    }

    fun updateProductList(newProductList: List<ProductsModel>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

    class ProductListViewHolder(var view: ItemProductBinding) :
        RecyclerView.ViewHolder(view.root)

}
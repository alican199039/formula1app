package com.aydemir.formula1app.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<M, DB : ViewDataBinding> constructor(private val viewDataBinding: DB) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    var item: M? = null

    abstract fun bind(data: M)

    fun setRowItem(data: M) {
        item = data
    }
    fun getRowItem(): M? {
        return item
    }

    fun getRowBinding(): DB? {
        return viewDataBinding
    }
}
package com.aydemir.formula1app.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.aydemir.formula1app.R
import com.aydemir.formula1app.base.BaseViewHolder
import com.aydemir.formula1app.databinding.RowDriverBinding
import com.aydemir.formula1app.model.data.Driver
import com.aydemir.formula1app.util.helper.FavoriteHelper
import timber.log.Timber

class DriverListAdapter(
    private val favoriteHelper: FavoriteHelper,
    private val list: MutableList<Driver>,
    private val clickListener: (Any, String) -> Unit
) :
    RecyclerView.Adapter<DriverListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        return ViewHolder(
            RowDriverBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.setRowItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_driver
    }

    fun addList(listToAdd: MutableList<Driver>) {
        list.addAll(listToAdd)
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: RowDriverBinding) :
        BaseViewHolder<Driver, RowDriverBinding>(binding), View.OnClickListener {

        init {
            getRowBinding()?.root?.setOnClickListener(this)
            getRowBinding()?.imgFavorite?.setOnClickListener(this)
        }

        override fun bind(data: Driver) {
            if (favoriteHelper.checkDriverInFavoriteList(data.id)) {
                getRowBinding()?.imgFavorite?.load(R.drawable.ic_bookmark_on)
            } else {
                getRowBinding()?.imgFavorite?.load(R.drawable.ic_bookmark_off)
            }
            getRowBinding()?.txtDriverPosition?.text = "${data.id}."
            getRowBinding()?.txtDriverName?.text = data.name
            getRowBinding()?.txtDriverPoint?.text = data.point.toString()
        }

        override fun onClick(v: View?) {
            if (v?.id == R.id.img_favorite) {
                getRowItem()?.let { rowItem ->
                    if (favoriteHelper.addDriverFavorite(rowItem)) {
                        getRowBinding()?.imgFavorite?.load(R.drawable.ic_bookmark_on)
                    } else {
                        getRowBinding()?.imgFavorite?.load(R.drawable.ic_bookmark_off)
                    }
                    Timber.i("FAV FAV FAV")
                }
            } else {
                getRowItem()?.let { rowItem ->
                    clickListener(rowItem, "")
                }
            }
        }
    }

}



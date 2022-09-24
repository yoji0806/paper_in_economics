package com.yoji0806.paperineconomics.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoji0806.paperineconomics.databinding.GridViewItemPaperBinding
import com.yoji0806.paperineconomics.network.PaperInfo

private const val TAG = "PaperGridAdapter"

class PaperGridAdapter : ListAdapter<PaperInfo,
        PaperGridAdapter.PaperInfoViewHolder>(PaperInfo.DIFF_UTIL) {

    inner class PaperInfoViewHolder(val binding: GridViewItemPaperBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(PaperInfo: PaperInfo) {
            binding.paper = PaperInfo
            binding.executePendingBindings()    //update views in this ViewHolder immediately
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperInfoViewHolder {
        Log.d(TAG, "[debug]: onCreateViewHolder() called!")
        return PaperInfoViewHolder(GridViewItemPaperBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: PaperInfoViewHolder, position: Int) {
        Log.d(TAG, "[debug]: onBindViewHolder() called!")
        val paperInfo = getItem(position)
        holder.bind(paperInfo)
    }
}
package com.yoji0806.paperineconomics.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoji0806.paperineconomics.databinding.GridViewItemPaperBinding
import com.yoji0806.paperineconomics.network.PaperInfo


class PaperGridAdapter : ListAdapter<PaperInfo,
        PaperGridAdapter.PaperInfoViewHolder>(PaperInfo.DIFF_UTIL) {

    inner class PaperInfoViewHolder(val binding: GridViewItemPaperBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(PaperInfo: PaperInfo) {
            binding.paper = PaperInfo
            binding.executePendingBindings()    //update views in this ViewHolder immediately
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperInfoViewHolder {
        return PaperInfoViewHolder(GridViewItemPaperBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: PaperInfoViewHolder, position: Int) {
        val paperInfo = getItem(position)
        holder.bind(paperInfo)
    }
}
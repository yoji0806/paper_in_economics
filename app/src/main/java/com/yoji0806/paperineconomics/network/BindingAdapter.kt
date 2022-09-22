package com.yoji0806.paperineconomics.network

import androidx.recyclerview.widget.RecyclerView
import com.yoji0806.paperineconomics.ui.home.PaperGridAdapter
import kotlinx.coroutines.awaitAll


fun bindingRecyclerView(recyclerView: RecyclerView,
                        data: List<PaperInfo>?) {
    val adapter = recyclerView.adapter as PaperGridAdapter
}
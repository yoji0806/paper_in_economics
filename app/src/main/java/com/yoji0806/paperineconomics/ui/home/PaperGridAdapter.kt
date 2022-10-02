package com.yoji0806.paperineconomics.ui.home

import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoji0806.paperineconomics.R
import com.yoji0806.paperineconomics.databinding.GridViewItemPaperBinding
import com.yoji0806.paperineconomics.network.PaperInfo
import com.yoji0806.paperineconomics.utility.DataUtil

private const val TAG = "PaperGridAdapter"

class PaperGridAdapter : ListAdapter<PaperInfo,
        PaperGridAdapter.PaperInfoViewHolder>(PaperInfo.DIFF_UTIL) {

    private val dataUtil = DataUtil()

    inner class PaperInfoViewHolder(val binding: GridViewItemPaperBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(PaperInfo: PaperInfo) {
            binding.paper = PaperInfo
            binding.executePendingBindings()    //update views in this ViewHolder immediately
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperInfoViewHolder {
        Log.d(TAG, "[debug]: onCreateViewHolder() called!")
        val view = LayoutInflater.from(parent.context)
        return PaperInfoViewHolder(
            GridViewItemPaperBinding.inflate(view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PaperInfoViewHolder, position: Int) {
        Log.d(TAG, "[debug]: onBindViewHolder() called!")
        val paperInfo = getItem(position)
        holder.bind(paperInfo)

        // get root view of holder(grid_view_item_paper.xml) -> layout view / MaterialCardView.
        holder.binding.root.setOnClickListener {
            Log.d(TAG, "[debug] setOnClickListener() called!")

            val textAuthors = dataUtil.convertAuthorListIntoOneLine(paperInfo.author_list)

            val action = HomeFragmentDirections.actionNavHomeToNavDetail(
                paperTitle = paperInfo.title_str,
                paperAuthors = textAuthors,
                paperAbstract = paperInfo.abstract_str,
                paperUrl = paperInfo.url,
                paperCategoryList = paperInfo.category_code_list.toTypedArray()
            )


            holder.binding.root.findNavController().navigate(action)
        }
    }
}
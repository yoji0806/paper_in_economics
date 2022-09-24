package com.yoji0806.paperineconomics.ui.home

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yoji0806.paperineconomics.R
import com.yoji0806.paperineconomics.databinding.GridViewItemPaperBinding
import com.yoji0806.paperineconomics.network.ApiService
import com.yoji0806.paperineconomics.network.Author
import com.yoji0806.paperineconomics.network.PaperInfo
import com.yoji0806.paperineconomics.utility.JournalUtil
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking



private const val LIMIT :Long = 2

class PaperGridAdapter : ListAdapter<PaperInfo,
        PaperGridAdapter.PaperInfoViewHolder>(PaperInfo.DIFF_UTIL) {
//RecyclerView.Adapter<PaperGridAdapter.PaperInfoViewHolder>() {


    inner class PaperInfoViewHolder(val binding: GridViewItemPaperBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(PaperInfo: PaperInfo) {
            binding.paper = PaperInfo
            binding.executePendingBindings()    //update views in this ViewHolder immediately
        }
    }

    private val viewModel = HomeViewModel()
    private val journalUtil = JournalUtil()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperInfoViewHolder {
//        val layout = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.grid_view_item_paper, parent, false)

//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = DataBindingUtil.inflate<GridViewItemPaperBinding>(
//            layoutInflater, R.layout.grid_view_item_paper, parent, false
//        )

        return PaperInfoViewHolder(GridViewItemPaperBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: PaperInfoViewHolder, position: Int) {

        val paperInfo = getItem(position)
        holder.bind(paperInfo)


        //TODO: use data binding and eliminate codes below.

//        // combine author's information in one line.
//        var authorsText = ""
//        var loopCounter = 1
//
//        for (author in item.author_list) {
//
//            authorsText += if (loopCounter > 1) {
//                "\n${author.name}  (${author.institution})"
//            } else {
//                "${author.name}  (${author.institution})"
//            }
//
//            loopCounter +=1
//        }
//
//        // get journal Logo image
//        val url = item.url
//        val journal = journalUtil.getJournalFromUrl(url)
//        val logo = journalUtil.getJournalLogo(journal)
//
//        // add category chip
//        for (category_code in item.category_code_list) {
//            val categoryName = journalUtil.jefCodeToClassification(category_code)
//            holder.binding.categoryGroup.addChip( holder.binding.categoryGroup.context , categoryName)
//        }
//
//        holder.binding.cardTitleText.text = item.title_str
//        holder.binding.cardAuthorsText.text = authorsText
//        holder.binding.cardThumbnail.setImageResource(logo)
//
//        holder.binding.cardResourceTitle.text = journal
    }




//    private fun ChipGroup.addChip(context: Context, label: String) {
//        Chip(context).apply {
//            id = View.generateViewId()
//            text = label
//            setTextAppearance(R.style.ChipCategory)
//            setEnsureMinTouchTargetSize(false)
//            addView(this)
//        }
//    }













}
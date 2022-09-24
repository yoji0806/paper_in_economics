package com.yoji0806.paperineconomics

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yoji0806.paperineconomics.network.Author
import com.yoji0806.paperineconomics.network.PaperInfo
import com.yoji0806.paperineconomics.ui.home.PaperGridAdapter
import com.yoji0806.paperineconomics.utility.JournalUtil


private val journalUtil = JournalUtil()


@BindingAdapter("listPaperInfoData")
fun bindingPaperRecyclerView(recyclerView: RecyclerView,
                             data: List<PaperInfo>?) {
    val adapter = recyclerView.adapter as PaperGridAdapter
    adapter.submitList(data)    // tells the RecyclerView when a new list is available.
    adapter.notifyDataSetChanged()
}


@BindingAdapter("adjustAndSetAuthorsText")
fun bindingPaperAuthorsTextView(textView: TextView,
                                author_list: List<Author>){
    // combine author's information in one line.
    var authorsText = ""
    var loopCounter = 1

    for (author in author_list) {

        authorsText += if (loopCounter > 1) {
            "\n${author.name}  (${author.institution})"
        } else {
            "${author.name}  (${author.institution})"
        }

        loopCounter +=1
    }

    textView.text = authorsText
}


@BindingAdapter("setJournalImage")
fun bindingPaperJournalImageView(imageView: ImageView, url: String) {

    val journal = journalUtil.getJournalFromUrl(url)
    val logo = journalUtil.getJournalLogo(journal)
    imageView.setImageResource(logo)
}


@BindingAdapter("setJournalText")
fun bindingJournalTextView(textView: TextView, url: String) {
    val journal = journalUtil.getJournalFromUrl(url)
    textView.text = journal
}


@BindingAdapter("addCategoryChips")
fun addCategoryChips(chipGroup: ChipGroup, categoryCodeList: List<String>) {

    // add category chip
    for (categoryCode in categoryCodeList) {
        val categoryName = journalUtil.jefCodeToClassification(categoryCode)
        chipGroup.addChip( chipGroup.context , categoryName)
    }
}

private fun ChipGroup.addChip(context: Context, label: String) {
    Chip(context).apply {
        id = View.generateViewId()
        text = label
        setTextAppearance(R.style.ChipCategory)
        setEnsureMinTouchTargetSize(false)
        addView(this)
    }
}



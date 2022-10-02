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
import com.yoji0806.paperineconomics.utility.DataUtil
import com.yoji0806.paperineconomics.utility.JournalUtil


private val journalUtil = JournalUtil()
private val dataUtil = DataUtil()


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
    val authorsText = dataUtil.convertAuthorListIntoOneLine(author_list)
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


@BindingAdapter("addCategoryChipsLarge")
fun addCategoryChipsLarge(chipGroup: ChipGroup, categoryCodeList: List<String>) {
    // add category chip
    for (categoryCode in categoryCodeList) {
        val categoryName = journalUtil.jefCodeToClassification(categoryCode)
        chipGroup.addChip( chipGroup.context , categoryName, R.style.ChipCategoryLarge)
    }
}

@BindingAdapter("addCategoryChipsSmall")
fun addCategoryChipsSmall(chipGroup: ChipGroup, categoryCodeList: List<String>) {
    // add category chip
    for (categoryCode in categoryCodeList) {
        val categoryName = journalUtil.jefCodeToClassification(categoryCode)
        chipGroup.addChip( chipGroup.context , categoryName, R.style.ChipCategorySmall)
    }
}



private fun ChipGroup.addChip(context: Context, label: String, style: Int) {
    Chip(context).apply {
        id = View.generateViewId()
        text = label
        setTextAppearance(style)
        setEnsureMinTouchTargetSize(false)
        addView(this)
    }
}



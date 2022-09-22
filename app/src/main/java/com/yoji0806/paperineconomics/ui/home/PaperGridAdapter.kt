package com.yoji0806.paperineconomics.ui.home

import android.content.Context
import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yoji0806.paperineconomics.R
import com.yoji0806.paperineconomics.network.ApiService
import com.yoji0806.paperineconomics.network.Author
import com.yoji0806.paperineconomics.network.PaperInfo
import com.yoji0806.paperineconomics.utility.JournalUtil
import kotlin.math.truncate

class PaperGridAdapter : RecyclerView.Adapter<PaperGridAdapter.PaperInfoViewHolder>() {


    // provide a reference of the view which will be displayed.
    class PaperInfoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val card = view.findViewById<MaterialCardView>(R.id.card_paper_small)!!
        val title = view.findViewById<TextView>(R.id.card_title_text)!!
        val authors = view.findViewById<TextView>(R.id.card_authors_text)!!
        val thumbnail = view.findViewById<ImageView>(R.id.card_thumbnail)!!
        val journal = view.findViewById<TextView>(R.id.card_resource_title)!!
        val categoryGroup = view.findViewById<ChipGroup>(R.id.categoryGroup)!!
    }

    //data read from DB.
    private val apiService = ApiService()
    //TODO for debug:
    private val limit :Long = 2
    //private val paperInfoList = apiService.getPaperInfoAll(limit=limit)
    //TODO: debug
    private val paper1 = PaperInfo(
        abstract_str= "There are one bad news, and one good news.",
        author_list = listOf(
            Author(
                name = "yoji yamamoto",
                institution = "Kobe U"
            )
        ),
        category_code_list = listOf("A20", "B55", "A19"),
        title_str = "hello this is paper 1",
        url = "https://www.aeaweb.org/journals/aer/issues"
    )
    private val paper2 = PaperInfo(
        abstract_str= "With great power comes great responsibility.",
        author_list = listOf(
            Author(
                name = "Kate Kuniwada",
                institution = "Tokyo U"
            )
        ),
        category_code_list = listOf("A20", "B55", "A19"),
        title_str = "hello this is paper 2",
        url = "https://www.aeaweb.org/journals/aer/issues"
    )

    private val paperInfoList = listOf<PaperInfo>(paper1, paper2)


    private val journalUtil = JournalUtil()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperInfoViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.grid_view_item_paper, parent, false)

        return PaperInfoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PaperInfoViewHolder, position: Int) {
        val item = paperInfoList[position]

        // combine author's information in one line.
        var authorsText = ""
        var loopCounter = 1
        for (author in item.author_list) {

            authorsText += if (loopCounter > 1) {
                "\n${author.name}  (${author.institution})"
            } else {
                "${author.name}  (${author.institution})"
            }

            loopCounter +=1
        }

        // get journal Logo image
        val url = item.url
        val journal = journalUtil.getJournalFromUrl(url)
        val logo = journalUtil.getJournalLogo(journal)

        // add category chip
        for (category_code in item.category_code_list) {
            val categoryName = journalUtil.jefCodeToClassification(category_code)
            holder.categoryGroup.addChip( holder.categoryGroup.context , categoryName)
        }

        holder.title.text = item.title_str
        holder.authors.text = authorsText
        holder.thumbnail.setImageResource(logo)
        holder.journal.text = journal
    }

    override fun getItemCount(): Int {
        return paperInfoList.size
    }


    private fun ChipGroup.addChip(context: Context, label: String) {
        Chip(context).apply {
            id = View.generateViewId()
            text = label
            isCheckable = false
            isClickable = false
            isCheckedIconVisible = false
            isFocusable = false
            addView(this)
        }
    }





}
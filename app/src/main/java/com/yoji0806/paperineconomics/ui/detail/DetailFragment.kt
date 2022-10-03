package com.yoji0806.paperineconomics.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yoji0806.paperineconomics.R
import com.yoji0806.paperineconomics.databinding.FragmentDetailBinding
import com.yoji0806.paperineconomics.network.ApiService
import com.yoji0806.paperineconomics.utility.JournalUtil

private const val TAG = "DetailFragment"
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private var isBookmarked = false
    private val viewModel: DetailViewModel by viewModels()

    private val apiService = ApiService()
    private val journalUtil = JournalUtil()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "[debug] args: $args!")
        viewModel.paperTitle = args.paperTitle
        viewModel.paperAuthors = args.paperAuthors
        viewModel.paperUrl = args.paperUrl
        viewModel.paperAbstract = args.paperAbstract
        viewModel.paperCategoryList = args.paperCategoryList.toList()

        binding.buttonBookmark.setOnClickListener{
            val paperUrl = args.paperUrl
            val navHeader = requireActivity()   //get reference
                .findViewById<NavigationView>(R.id.nav_view).getHeaderView(0)
            val hiddenTextView = navHeader.findViewById<TextView>(R.id.hidden_text_uid)
            val userUid = hiddenTextView.text.toString()
            changeBookmark(
                userId = userUid,
                paperUrl = paperUrl
            )
        }
    }

    fun changeBookmark(userId: String, paperUrl: String) {

        val paperId = journalUtil.getDoiFromUrl(paperUrl)

        isBookmarked = !isBookmarked
        if (isBookmarked){
            apiService.setBookmark(
                userId = userId,
                paperId = paperId
            )
            binding.buttonBookmark.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
        }else{
            apiService.unSetBookmark(
                userId = userId,
                paperId = paperId
            )
            binding.buttonBookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_grey_40)
        }
    }

}
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
import com.yoji0806.paperineconomics.ui.home.HomeFragmentDirections
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

    private lateinit var userId: String
    private lateinit var paperId: String


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

        val navHeader = requireActivity()   //get reference
            .findViewById<NavigationView>(R.id.nav_view).getHeaderView(0)
        val hiddenTextView = navHeader.findViewById<TextView>(R.id.hidden_text_uid)
        userId = hiddenTextView.text.toString()
        paperId = journalUtil.getDoiFromUrl(args.paperUrl)

        viewModel.checkBookmark(
            userId = userId,
            paperId = paperId,
            fragment = this
        )

        Log.d(TAG, "[debug] args: $args!")
        viewModel.paperTitle = args.paperTitle
        viewModel.paperAuthors = args.paperAuthors
        viewModel.paperUrl = args.paperUrl
        viewModel.paperAbstract = args.paperAbstract
        viewModel.paperCategoryList = args.paperCategoryList.toList()

        binding.buttonBookmark.setOnClickListener{
            changeBookmark(
                userId = userId,
                paperId = paperId
            )
        }
    }


    fun unBookmark() {
        isBookmarked = false
        binding.buttonBookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_grey_40)
    }

    fun bookmark() {
        isBookmarked = true
        binding.buttonBookmark.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
    }

    private fun changeBookmark(userId: String, paperId: String) {

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
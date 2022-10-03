package com.yoji0806.paperineconomics.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yoji0806.paperineconomics.R
import com.yoji0806.paperineconomics.databinding.FragmentDetailBinding

private const val TAG = "DetailFragment"
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private var isBookmarked = false
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        database = Firebase.database.reference

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.buttonBookmark.setOnClickListener{
            isBookmarked = !isBookmarked
            if (isBookmarked){
                binding.buttonBookmark.setImageResource(R.drawable.ic_baseline_bookmark_added_24)
            }else{
                binding.buttonBookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_grey_40)
            }
        }

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
    }

}
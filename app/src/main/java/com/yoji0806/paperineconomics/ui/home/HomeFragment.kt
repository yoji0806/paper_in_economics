package com.yoji0806.paperineconomics.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.navigation.NavigationView
import com.yoji0806.paperineconomics.R
import com.yoji0806.paperineconomics.databinding.FragmentHomeBinding
import com.yoji0806.paperineconomics.network.PaperInfo
import com.yoji0806.paperineconomics.utility.DataUtil

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: HomeFragmentArgs by navArgs()

    private lateinit var recyclerView: RecyclerView

    private val viewModel: HomeViewModel by viewModels()

    private val dataUtil = DataUtil()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //change text and image in the NavigationView-------------
        val userName = args.userName
        val email = args.emailAddress
        val imageUrl = args.imageUrl
            .toUri().buildUpon().scheme("https").build()

        //TODO: replace with data binding after implementing User viewModel
        val navHeader = requireActivity()   //get reference
            .findViewById<NavigationView>(R.id.nav_view).getHeaderView(0)

        val userNameTextView = navHeader.findViewById<TextView>(R.id.menu_text_user_name)
        val emailTextView = navHeader.findViewById<TextView>(R.id.menu_text_email)
        val profileImageView = navHeader.findViewById<ImageView>(R.id.profile_image)

        userNameTextView.text = userName
        emailTextView.text = email
        profileImageView.load(imageUrl)
        //-------------------------------------------------------end



        //configure the Recycler View ---------------------------
        val paperGridAdapter = PaperGridAdapter()

        //update list later.
        //TODO does this still need?
        viewModel.paperInfoList.observe(viewLifecycleOwner, Observer {
            paperGridAdapter.submitList(it)
        })

        recyclerView = binding.recyclerViewNew
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = paperGridAdapter
        //------------------------------------------------------end


    }

    //TODO does this still need?
//    override fun onResume() {
//        super.onResume()
//        //get data from DB.
//        viewModel.getNewPaperList()
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
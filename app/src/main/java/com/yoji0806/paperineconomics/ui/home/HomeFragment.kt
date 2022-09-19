package com.yoji0806.paperineconomics.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.navigation.NavigationView
import com.yoji0806.paperineconomics.R
import com.yoji0806.paperineconomics.databinding.FragmentHomeBinding

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = args.emailAddress
        val imageUrl = args.imageUrl
            .toUri().buildUpon().scheme("https").build()

        //TODO: replace with data binding after implementing User viewModel

        //change text and image in NavigationView
        val navHeader = requireActivity()   //get reference
            .findViewById<NavigationView>(R.id.nav_view).getHeaderView(0)

        val emailTextView = navHeader.findViewById<TextView>(R.id.menuTextEmail)
        val profileImageView = navHeader.findViewById<ImageView>(R.id.profileImage)

        emailTextView.text = email
        profileImageView.load(imageUrl)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
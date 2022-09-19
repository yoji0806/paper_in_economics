package com.yoji0806.paperineconomics.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.yoji0806.paperineconomics.databinding.FragmentLoginBinding


private const val TAG = "LoginFragment"

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //TODO viewModel needed ?

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.buttonGoogleLogin.setOnClickListener {
            createSignInIntent()
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val durationShort = Toast.LENGTH_SHORT
        val response = result.idpResponse

        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            //Successfully signed in
            Log.d(TAG, "[debug]: FirebaseAuthentication succeed!")
            Log.d(TAG, "[debug]: response: " + response.toString())

            Toast.makeText(requireContext(), "Login Succeeded!", durationShort)
                .show()

            val email = response?.email.toString()
            val action = LoginFragmentDirections.actionLoginFragmentToNavHome(email)
            findNavController().navigate(action)

            //TODO: implement this method if needed
            //val user = FirebaseAuth.getInstance().currentUser
            //updateUI(currentUser)

            //TODO: save user info in local DB


        } else {
            //Sign in failed. If response is null the user canceled the sign-in flow using the back button. Otherwise check response.getError().getErrorCode() and handle the error.
            Log.d(TAG, "[debug]: FirebaseAuthentication failed!")
            Toast.makeText(requireContext(), "Login failed", durationShort)
                .show()
        }
    }


    private fun createSignInIntent() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }


    private fun signOut() {
        AuthUI.getInstance()
            .signOut(requireContext())
            .addOnCompleteListener {
                // TODO: sign out implementation.
            }
    }

}
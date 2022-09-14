package com.yoji0806.paperineconomics

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.yoji0806.paperineconomics.databinding.ActivityLoginBinding


//TODO: downsize image file -> change to xml file or downgrade.

private const val TAG = "LoginActivity"


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonGoogleLogin.setOnClickListener {
            createSignInIntent()
        }
    }




    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            //Successfully signed in
            Log.d(TAG, "[debug]: FirebaseAuthentication succeed!")
            Log.d(TAG, "[debug]: response: " + response.toString())
            val user = FirebaseAuth.getInstance().currentUser
            //TODO: implement this method if needed
            //updateUI(currentUser)



        } else {
            //Sign in failed. If response is null the user canceled the sign-in flow using the back button. Otherwise check response.getError().getErrorCode() and handle the error.
            Log.d(TAG, "[debug]: FirebaseAuthentication failed!")
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
            .signOut(this)
            .addOnCompleteListener {
                // TODO: sign out implementation.
            }
    }
}
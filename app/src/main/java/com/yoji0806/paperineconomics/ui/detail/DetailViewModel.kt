package com.yoji0806.paperineconomics.ui.detail

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.sql.Driver

private const val TAG = "DetailViewModel.kt"

class DetailViewModel() : ViewModel() {

    var paperTitle = ""
    var paperAuthors = ""
    var paperUrl = ""
    var paperAbstract  = ""
    var paperCategoryList = listOf<String>()



    private val dbRealtime = Firebase.database.reference


    fun checkBookmark (userId: String, paperId: String, fragment: DetailFragment) {

        Log.d(TAG, "[debug]: userId: $userId, paperId: $paperId")

        dbRealtime.child(userId).child(paperId).child("bookmarked").get().addOnSuccessListener {
            Log.d(TAG, "[debug]: got value from Firebase RealtimeDB: ${it.value}")

            if (it.value == true){
                fragment.bookmark()
            } else {
                fragment.unBookmark()
            }

        }.addOnFailureListener{
            Log.e(TAG, "[error]: Error getting data from Firebase RealtimeDB: ", it)
        }
    }
}

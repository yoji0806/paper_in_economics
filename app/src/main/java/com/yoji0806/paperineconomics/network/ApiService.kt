package com.yoji0806.paperineconomics.network

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yoji0806.paperineconomics.ui.home.PaperGridAdapter

private const val TAG = "[ApiService]"


class ApiService {

    private val dbFirestore = Firebase.firestore
    private val dbRealtime = Firebase.database.reference

    fun setBookmark (userId:String, paperId:String) {
        val paperOperation = PaperOperation(isBookmarked = true)
        dbRealtime.child(userId).child(paperId).setValue(paperOperation)
    }

    fun unSetBookmark (userId: String, paperId: String) {
        val paperOperation = PaperOperation(isBookmarked = false)
        dbRealtime.child(userId).child(paperId).setValue(paperOperation)
    }


    //TODO
    fun getPaperInfoWithQuery(limit:Long = 0): MutableList<PaperInfo> {
        val paperInfoList : MutableList<PaperInfo> = mutableListOf()

        val collection = "papers"
        dbFirestore.collection(collection)
            .limit(limit)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val doi = document.id
                    val paperInfo = document.toObject(PaperInfo::class.java)
                    paperInfoList.add(paperInfo)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return paperInfoList
    }


    //TODO remove limit parameter for dev.
    fun getPaperInfoAll(limit:Long = 2): List<PaperInfo> {
        val paperInfoList : MutableList<PaperInfo> = mutableListOf()
        val collection = "papers"

        dbFirestore.collection(collection)
            .limit(limit)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val doi = document.id
                    val paperInfo = document.toObject(PaperInfo::class.java)
                    paperInfoList.add(paperInfo)
                }

                //TODO: need to add this list to HomeViewModel._paperInfoList
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return paperInfoList
    }
}


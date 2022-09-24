package com.yoji0806.paperineconomics.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yoji0806.paperineconomics.DataForDev
import com.yoji0806.paperineconomics.network.ApiService
import com.yoji0806.paperineconomics.network.Author
import com.yoji0806.paperineconomics.network.PaperInfo
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus { LOADING, ERROR, DONE }
private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    // stores the status of the most recent request.
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status           //TODO: implement bindingAdapter to show status error message with this variable.

    //TODO debug
    private val _paperInfoList = MutableLiveData<List<PaperInfo>>()
    val paperInfoList: LiveData<List<PaperInfo>> = _paperInfoList

    private val apiService = ApiService()


    init {
        getNewPaperList()
    }


    fun getNewPaperList(){
        // for local development
        val debug = DataForDev().debug
        if (debug) viewModelScope.launch{
            try {
                Log.d(TAG, "[debug]: _paperInfoList.value: ${_paperInfoList.value}")
                _paperInfoList.value = getDummyPaperInfoAll()
                _status.value = ApiStatus.DONE
                Log.d(TAG, "[debug]: _paperInfoList.value: ${_paperInfoList.value}")
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _paperInfoList.value = listOf()
            }

        // for deployed app
        } else viewModelScope.launch {
            try {
                Log.d(TAG, "[debug]: _paperInfoList.value: ${_paperInfoList.value}")
                //TODO: debug
                //_paperInfoList.value = apiService.getPaperInfoAll()
                getPaperInfoAll(2)

                _status.value = ApiStatus.DONE
                Log.d(TAG, "[debug]: _paperInfoList.value: $_paperInfoList.value")
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _paperInfoList.value = listOf()
            }
        }
    }


    private fun getDummyPaperInfoAll() : List<PaperInfo>{

        val paper1 = PaperInfo(
            abstract_str = "There are one bad news, and one good news.",
            author_list = listOf(
                Author(
                    name = "yoji yamamoto",
                    institution = "Kobe U"
                )
            ),
            category_code_list = listOf("A20", "B55", "A19"),
            title_str = "Productivity Shocks, Long-Term Contracts, and Earnings Dynamics",
            url = "https://www.aeaweb.org/journals/aer/issues"
        )

        val paper2 = PaperInfo(
            abstract_str = "With great power comes great responsibility.",
            author_list = listOf(
                Author(
                    name = "Kate Kuniwada",
                    institution = "Tokyo U"
                )
            ),
            category_code_list = listOf("A20", "B55", "A19"),
            title_str = "Aggregating Distributional Treatment Effects: A Bayesian Hierarchical Analysis of the Microcredit Literature",
            url = "https://www.aeaweb.org/journals/aer/issues"
        )

        return listOf(paper1, paper2)
    }

    private fun getPaperInfoAll(limit:Long = 2): List<PaperInfo> {
        val paperInfoList : MutableList<PaperInfo> = mutableListOf()

        val db = Firebase.firestore
        val collection = "papers"

        val adapter = PaperGridAdapter()

        db.collection(collection)
            .limit(limit)
            .get()
            .addOnSuccessListener { documents ->
                Log.d(TAG, "[debug] adapter.itemCount: ${adapter.itemCount}")
                Log.d(TAG, "[debug] adapter.adapter.hasObservers(): ${adapter.hasObservers()}")
                for (document in documents) {
                    val doi = document.id
                    val paperInfo = document.toObject(PaperInfo::class.java)
                    paperInfoList.add(paperInfo)
                }
                Log.d(TAG, "[debug] ${paperInfoList.count()} items fetched.")
                Log.d(TAG, "[debug]: ${paperInfoList}")
//                adapter.submitList(paperInfoList)
//                adapter.notifyItemRangeChanged(0,2)
                _paperInfoList.value = paperInfoList
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        return paperInfoList
    }

}





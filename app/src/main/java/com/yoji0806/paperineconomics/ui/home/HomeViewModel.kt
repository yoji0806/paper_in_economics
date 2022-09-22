package com.yoji0806.paperineconomics.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yoji0806.paperineconomics.network.ApiService
import com.yoji0806.paperineconomics.network.PaperInfo
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.reflect.Type
import java.util.*

enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {

    // stores the status of the most recent request.
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _paperInfoList = MutableLiveData<List<PaperInfo>>()
    val paperInfoList: LiveData<List<PaperInfo>> = _paperInfoList



    //TODO remove after implement.
    private val _text = MutableLiveData<String>().apply {
       value = "iei"
    }
    val text: LiveData<String> = _text


    //TODO for debug
    private val apiService = ApiService()
    private fun getPaperInfoList() {



    }

}





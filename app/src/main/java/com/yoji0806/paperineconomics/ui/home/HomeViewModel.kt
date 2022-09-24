package com.yoji0806.paperineconomics.ui.home

import androidx.lifecycle.*
import com.yoji0806.paperineconomics.DataForDev
import com.yoji0806.paperineconomics.network.ApiService
import com.yoji0806.paperineconomics.network.Author
import com.yoji0806.paperineconomics.network.PaperInfo
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {

    // stores the status of the most recent request.
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status           //TODO: implement bindingAdapter to show status error message with this variable.

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
                _paperInfoList.value = getDummyPaperInfoAll()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _paperInfoList.value = listOf()
            }

        // for deployed app
        } else viewModelScope.launch {
            try {
               _paperInfoList.value = apiService.getPaperInfoAll()
                _status.value = ApiStatus.DONE
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
}





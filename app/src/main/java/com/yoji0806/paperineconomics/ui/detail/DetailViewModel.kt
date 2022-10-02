package com.yoji0806.paperineconomics.ui.detail

import androidx.lifecycle.ViewModel

class DetailViewModel() : ViewModel() {

    var paperTitle = ""
    var paperAuthors = ""
    var paperUrl = ""
    var paperAbstract  = ""
    var paperCategoryList = listOf<String>()
}
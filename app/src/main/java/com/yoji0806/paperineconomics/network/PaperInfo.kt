package com.yoji0806.paperineconomics.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.grpc.internal.DnsNameResolver



data class PaperInfo(
    val abstract_str: String = "",
    val author_list: List<Author> = listOf(),
    val category_code_list: List<String> = listOf(),
    val title_str : String = "",
    val url : String = ""
)


data class Author(
    val name : String = "",
    val institution : String = ""
)



//data class PaperInfo(
//    val id : String = "",
//    @Json(name = "abstract_str") val abstract: String = "",
//    @Json(name = "author_list") val authors: List<Author> = listOf(),
//    @Json(name = "categorycode_lst") val categoryCodes: List<String> = listOf(),
//    @Json(name = "title_str") val title: String = "",
//    @Json(name = "url") val url: String = ""
//)
//
//
//data class Author(
//    @Json(name = "name") val name: String = "",
//    @Json(name ="institution") val institution: String = ""
//)


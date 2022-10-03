package com.yoji0806.paperineconomics.network

data class User (
    val userId: String = "",
    val userName: String = "",
    val email: String = "",
    // if a paper is stored in this location, this means the user have read at least once.
    val paperRead: List<String> = listOf(),
    val categoryFollowed: List<String> = listOf(),
    val journalFollowed: List<String> = listOf()
){

}

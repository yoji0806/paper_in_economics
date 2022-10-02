package com.yoji0806.paperineconomics.utility

import com.yoji0806.paperineconomics.network.Author

class DataUtil {

    fun convertAuthorListIntoOneLine (author_list: List<Author>): String {
        // combine author's information in one line.
        //  format: "<Author's name>  (<Author's institution>)\n<Author's name..."
        var authorsText = ""
        var loopCounter = 1

        for (author in author_list) {

            authorsText += if (loopCounter > 1) {
                "\n${author.name}  (${author.institution})"
            } else {
                "${author.name}  (${author.institution})"
            }

            loopCounter +=1
        }
        return authorsText
    }
}
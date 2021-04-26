package com.android.booklist

class Books(private var mTitle: String = "No title", private var mAuthors: String = "No Author", private var mYear: String = "No Year") {

    init {
        if (mTitle == ""){
            mTitle = "No title"
        }
        if (mAuthors == ""){
            mAuthors = "No Author"
        }
        if (mYear == ""){
            mYear = "No Year"
        }
    }

    public fun getTitle(): String{
        return mTitle
    }
    public fun getAuthors(): String{
        return mAuthors
    }
    public fun getYear(): String{
        return mYear
    }
}
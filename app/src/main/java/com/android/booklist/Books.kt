package com.android.booklist

import java.io.Serializable

class Books(private var mAuthors: String): Serializable{
    private var mTitle: String = ""
    private var mThumbnail: String = ""
    private var mDescription: String = ""
    private var mPgCount: String = ""
    private var mSubtitle: String = ""
    private var mGenre: String = ""
    private var mYear: String = ""

    init {
        if (mTitle.isEmpty()){
            mTitle = "No title"
        }
        if (mAuthors.isEmpty()){
            mAuthors = "No Author"
        }
        if (mYear.isEmpty()){
            mYear = "No Year"
        }
        if (mDescription.isEmpty()){
            mDescription = "No description"
        }
        if (mPgCount.isEmpty()){
            mPgCount = "No count available"
        }
        if (mGenre.isEmpty()){
            mGenre = "Not available"
        }
    }
    fun setTitle(title: String){
        mTitle = title
    }
    fun setThumbnail(tn: String){
        mThumbnail = tn
    }

    fun setDescription(des: String){
        mDescription = des
    }
    fun setYear(year: String){
        mYear = year
        mYear = mYear.substring(0, 4)
    }
    fun setSubtitle(sub: String){
        mSubtitle = sub
        mSubtitle = "\"$mSubtitle\""
    }
    fun getSubtitle(): String{
        return mSubtitle
    }
    fun setPageCount(pg: String){
        mPgCount = pg
    }
    fun getPgCount(): String{
        return mPgCount
    }
    fun setGenre(genre: String){
        mGenre = genre
    }
    fun getGenre(): String{
        return mGenre
    }
    fun getTitle(): String{
        return mTitle
    }
    fun getAuthors(): String{
        return mAuthors
    }
    fun getYear(): String{
        return mYear
    }
    fun getDescription(): String{
        return mDescription
    }
    fun getThumbnail(): String{
        return mThumbnail
    }
}
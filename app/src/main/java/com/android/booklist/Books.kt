package com.android.booklist

class Books(private var mTitle: String = "No title", private var mAuthors: String = "No Author"){
    private var mThumbnail: String = ""
    private var mDescription: String = ""
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
    }

    public fun setThumbnail(tn: String){
        mThumbnail = tn
    }

    public fun setDescription(des: String){
        mDescription = des
    }
    public fun setYear(year: String){
        mYear = year
        mYear = mYear.substring(0, 4)
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
    public fun getDescription(): String{
        return mDescription
    }
    public fun getThumbnail(): String{
        return mThumbnail
    }
}
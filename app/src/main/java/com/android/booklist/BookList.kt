package com.android.booklist

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookList : AppCompatActivity(), LoaderManager.LoaderCallbacks<ArrayList<Books?>> {
    private lateinit var mQuery: String
    private lateinit var  mAdapter: CustomizedAdapter
    private lateinit var mEmptyTextView: TextView

    companion object{
        val BOOK_API = "https://www.googleapis.com/books/v1/volumes?"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        val listView = findViewById<RecyclerView>(R.id.recyclerView)
        listView.layoutManager = LinearLayoutManager(this)
        val bundle = intent.extras
        mQuery = bundle!!.get("query").toString()
        mEmptyTextView = findViewById(R.id.empty_textView)
        mAdapter = CustomizedAdapter(this, ArrayList())

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected){
            LoaderManager.getInstance(this).initLoader(1, null, this)
        }
        else{
            val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
            progressBar.visibility = View.GONE
            mEmptyTextView.text = getString(R.string.no_internet)
        }
        listView.adapter = mAdapter
    }


    class SearchBook(context: Context, url: String, query: String): AsyncTaskLoader<ArrayList<Books?>>(context){
        private var mUrl = url
        private var mQuery = query
        override fun onStartLoading() {
            forceLoad()
        }
        override fun loadInBackground(): ArrayList<Books?> {
            val QUERY_PARA: String = "q"
            val TYPE: String = "printType"
            val MAX_RESULTS: String = "maxResults"
            val url = Uri.parse(mUrl).buildUpon().appendQueryParameter(QUERY_PARA, mQuery).appendQueryParameter(TYPE, "books").appendQueryParameter(MAX_RESULTS, "10").build()
            return QueryUtils.fetchBooks(url)
        }

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<ArrayList<Books?>> {
        return SearchBook(this, BOOK_API, mQuery)
    }

    override fun onLoadFinished(loader: Loader<ArrayList<Books?>>, data: ArrayList<Books?>?){
        var progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.visibility = View.GONE
        mAdapter.update(data!!)
    }

    override fun onLoaderReset(loader: Loader<ArrayList<Books?>>) {

    }


}
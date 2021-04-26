package com.android.booklist

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity(){
    private lateinit var mQuery: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val search = findViewById<Button>(R.id.search)

        search.setOnClickListener {
            val queryText = findViewById<EditText>(R.id.query)
            mQuery = queryText.text.toString()
            val intent = Intent(this, BookList::class.java)
            intent.putExtra("query", mQuery)
            intent.action = Intent.ACTION_SEND
            startActivity(intent)
        }


    }
}
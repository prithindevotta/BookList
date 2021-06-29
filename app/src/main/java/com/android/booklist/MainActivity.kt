package com.android.booklist

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(){
    private lateinit var mQuery: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val search = findViewById<ImageView>(R.id.search)
        val queryText = findViewById<EditText>(R.id.query)
        search.setOnClickListener {
            queryText.isFocusable = true
            queryText.requestFocus()
            val imm = this.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(queryText, 0)
        }
        queryText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()

            }
            false
        }


    }

    private fun performSearch() {
        val queryText = findViewById<EditText>(R.id.query)
        queryText.clearFocus()
        val `in`: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        `in`.hideSoftInputFromWindow(queryText.windowToken, 0)
        mQuery = queryText.text.toString()
        queryText.text.clear()

        val intent = Intent(this, BookList::class.java)
        intent.putExtra("query", mQuery)
        intent.action = Intent.ACTION_SEND
        startActivity(intent)
    }
}
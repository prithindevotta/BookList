package com.android.booklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomizedAdapter(context: Context, books: ArrayList<Books?>): ArrayAdapter<Books>(context, 0, books) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val targetView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_view, parent, false)
        val book = getItem(position)

        val title = targetView.findViewById<TextView>(R.id.title)
        title.text = book!!.getTitle()

        val authors = targetView.findViewById<TextView>(R.id.authors)
        authors.text = book.getAuthors()

        val year = targetView.findViewById<TextView>(R.id.year)
        year.text = book.getYear()

        return targetView
    }
}
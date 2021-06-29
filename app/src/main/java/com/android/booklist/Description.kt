package com.android.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class Description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val book = intent.getSerializableExtra("clicked") as Books?
        val imageView = findViewById<ImageView>(R.id.thumbnail)
        Glide.with(this).load(book!!.getThumbnail()).into(imageView)
        val title = findViewById<TextView>(R.id.title)
        title.text = book.getTitle()
        val subtitle = findViewById<TextView>(R.id.subtitle)
        subtitle.text = book.getSubtitle()
        val author = findViewById<TextView>(R.id.authors)
        author.text = "by: "+book.getAuthors()
        val publishD = findViewById<TextView>(R.id.yearValue)
        publishD.text = " "+book.getYear()
        val description = findViewById<TextView>(R.id.description)
        description.text = book.getDescription()
        val pgCount = findViewById<TextView>(R.id.pgValue)
        pgCount.text = book.getPgCount()
        val genre = findViewById<TextView>(R.id.genValue)
        genre.text = book.getGenre()
    }
}
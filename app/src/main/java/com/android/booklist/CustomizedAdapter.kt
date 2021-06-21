package com.android.booklist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.InputStream
import java.net.URL


class CustomizedAdapter(context: Context, var  books: ArrayList<Books?>): RecyclerView.Adapter<ImageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val targetView = LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        var viewHolder =  ImageHolder(targetView)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val currentItem = books[position]
        holder.titleView.text = currentItem!!.getTitle()
        holder.authors.text = currentItem.getAuthors()
        holder.year.text = currentItem.getYear()
        Glide.with(holder.itemView.context).load(currentItem.getThumbnail()).into(holder.imageView);
    }

    override fun getItemCount(): Int {
        return books.size
    }
    fun update(updatedArray: ArrayList<Books?>) {
        books.clear()
        books.addAll(updatedArray)
        notifyDataSetChanged()
    }
}
class ImageHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
    val authors = itemView.findViewById<TextView>(R.id.authors)
    val year = itemView.findViewById<TextView>(R.id.year)
    val imageView = itemView.findViewById<ImageView>(R.id.thumbnail)
}
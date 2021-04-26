package com.android.booklist

import android.net.Uri
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

class QueryUtils {
    companion object{
        val LOG_TAG: String = QueryUtils::class.java.simpleName

        fun fetchBooks(queryUrl: Uri?): ArrayList<Books?>{
            val url: URL? = createUrl(queryUrl.toString())
            var jsonResponse: String? = null
            try {
                jsonResponse = makeHttpConnection(url)
            }
            catch (e: IOException){
                Log.e(LOG_TAG, "error closing input stream", e)
            }

            return extractBooks(jsonResponse)
        }

        private fun extractBooks(jsonResponse: String?): ArrayList<Books?> {
            var books: ArrayList<Books?> = ArrayList()
            var jsonObject = JSONObject(jsonResponse!!)
            val jsonArray = jsonObject.getJSONArray("items")
            for (i in 0 until jsonArray.length()){
                val item = jsonArray.getJSONObject(i)
                val volume = item.getJSONObject("volumeInfo")
                val authors = StringBuilder()
                if(volume.has("authors")){
                    val authorsArray = volume.getJSONArray("authors")
                    for (i in 0 until authorsArray.length()){
                        if (i==0){
                            authors.append(authorsArray.get(i).toString())
                        }
                        else{
                            authors.append(", "+authorsArray.get(i).toString())
                        }
                    }
                }
                books.add(Books(volume.getString("title"), authors.toString(), volume.getString("publishedDate")))
            }
            return books
        }

        private fun makeHttpConnection(url: URL?): String? {
            var urlConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null

            var jsonResponse: String? = null

            if (url==null){
                return jsonResponse
            }
            try {
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.readTimeout = 10000
                urlConnection.connectTimeout = 15000
                urlConnection.requestMethod = "GET"
                urlConnection.connect()

                if (urlConnection.responseCode == 200){
                    inputStream = urlConnection.inputStream
                    jsonResponse = readInputStream(inputStream)
                }
                else{
                    Log.e(LOG_TAG, "url not found: ${urlConnection.responseCode}")
                }
            }
            catch (e: IOException){
                Log.e(LOG_TAG, "problem retrieving json")
            }
            finally {
                urlConnection?.disconnect()
                inputStream?.close()
            }
            return  jsonResponse
        }

        private fun readInputStream(inputStream: InputStream?): String? {
            var output = StringBuilder()
            val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
            val reader = BufferedReader(inputStreamReader)
            var line = reader.readLine()
            while (line != null){
                output.append(line)
                line = reader.readLine()
            }
            if (output.isEmpty()){
                Log.v(LOG_TAG, "output is empty")
            }
            return output.toString()
        }

        private fun createUrl(queryUrl: String?): URL? {
            var url: URL? = null
            if (queryUrl!!.isEmpty()){
                return url
            }
            try {
                url = URL(queryUrl)
            }
            catch (e: MalformedURLException){
                Log.e(LOG_TAG,"URL empty", e)
            }

            return url
        }
    }
}
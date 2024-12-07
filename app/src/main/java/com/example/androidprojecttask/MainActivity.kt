package com.example.androidprojecttask

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnecdoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AnecdoteAdapter(emptyList())
        recyclerView.adapter = adapter


        val randomNumber = (1..100).random()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://baneks.ru/$randomNumber")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val html = response.body?.string()
                val doc = Jsoup.parse(html ?: "")

                val article = doc.select("article")
                val p = article.select("p")

                val anecdoteText = p.text()
                val anecdotes = listOf(Anecdote(1, anecdoteText))
                runOnUiThread {
                    adapter.updateAnecdotes(anecdotes)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}

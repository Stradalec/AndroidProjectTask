package com.example.androidprojecttask

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
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
        adapter = AnecdoteAdapter(mutableListOf())
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val listOfAnecdotes = mutableListOf<Anecdote>()
            for (index in 1..10) {
                val randomNumber = (1..2000).random()
                val response = fetchAnecdote(randomNumber)
                if (response.isSuccessful) {
                    val html = response.body?.string()
                    val doc = Jsoup.parse(html ?: "")
                    val article = doc.select("article")
                    val p = article.select("p")
                    val anecdoteText = p.text()
                    val parsedAnecdote = Anecdote(randomNumber, anecdoteText)
                    listOfAnecdotes.add(parsedAnecdote)
                } else {

                }
            }
            withContext(Dispatchers.Main) {
                adapter.updateAnecdotes(listOfAnecdotes)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private suspend fun fetchAnecdote(randomNumber: Int): Response {
        return withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://baneks.ru/$randomNumber")
                .build()
            client.newCall(request).execute()
        }
    }


}



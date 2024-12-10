package com.example.androidprojecttask

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AnecdoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anecdote)

        val text = intent.getStringExtra("anecdote_text")
        val textView = findViewById<TextView>(R.id.anecdote_text)
        textView.text = text
    }
}

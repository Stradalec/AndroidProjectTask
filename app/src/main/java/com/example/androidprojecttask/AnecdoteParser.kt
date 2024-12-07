package com.example.androidprojecttask

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class AnecdoteParser {
    /*fun parseAnecdotes(html: String): List<Anecdote> {
        val doc: Document = Jsoup.parse(html)
        val anecdotes = mutableListOf<Anecdote>()
        val article = doc.select("article")
        val p = article.select("p")
        val elements: Elements = doc.select("div.anek")
        elements.forEach { element ->
            val id = element.attr("id").toInt()
            val text = p.text()
            anecdotes.add(Anecdote(id, text))
        }

        return anecdotes
    }*/
}

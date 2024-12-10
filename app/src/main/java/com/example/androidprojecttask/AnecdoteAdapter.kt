package com.example.androidprojecttask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnecdoteAdapter(private var anecdotes: List<Anecdote>) :
    RecyclerView.Adapter<AnecdoteAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(anecdote: Anecdote)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anecdote, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(anecdotes[position])
    }

    override fun getItemCount(): Int {
        return anecdotes.size
    }
    fun updateAnecdotes(newAnecdotes: List<Anecdote>) {
        anecdotes = newAnecdotes
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.findViewById(R.id.text)
        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(anecdotes[adapterPosition])
            }
        }
        fun bind(anecdote: Anecdote) {
            text.text = anecdote.text
        }
    }
}

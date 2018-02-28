package com.example.pastkhuf.songbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView

class SongAdapter(private val items: ArrayList<Song>): RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SongAdapter.ViewHolder?, position: Int) {
        holder?.txtName?.text = items[position].title

        holder?.txtAuthors?.text = items[position].authors.joinToString(",")
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SongAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.song_list_row, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val txtName: TextView = row.findViewById<TextView>(R.id.txtName)
        val txtAuthors: TextView = row.findViewById<TextView>(R.id.txtComment)
    }
}

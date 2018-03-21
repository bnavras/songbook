package com.example.pastkhuf.songbook

import java.io.Serializable

data class Song(val authors: ArrayList<Author>,
                val body: String,
                val body_chords_html: String,
                val body_stripped: String,
                val chords: Chord.List,
                val id: Long,
                val permalink: String,
                val tags: ArrayList<String>,
                val title: String,
                val uri: String) : Serializable {

    override fun toString(): String {
        return title
    }

    class List : ArrayList<Song>()
}

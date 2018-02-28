package com.example.pastkhuf.songbook

data class Chord(val code: String,
                 val image_url: String,
                 val instrument: Instrument,
                 val name: String,
                 val uri: String){

    class List: ArrayList<Chord>()
}

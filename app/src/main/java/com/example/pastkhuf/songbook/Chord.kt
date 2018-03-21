package com.example.pastkhuf.songbook

import java.io.Serializable

data class Chord(val code: String,
                 val image_url: String,
                 val instrument: Instrument,
                 val name: String,
                 val uri: String): Serializable {
}

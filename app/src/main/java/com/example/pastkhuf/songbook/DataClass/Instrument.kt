package com.example.pastkhuf.songbook

import java.io.Serializable

data class Instrument(val name: String,
                      val safe_name: String,
                      val tuning: String) : Serializable {
}
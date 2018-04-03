package com.example.pastkhuf.songbook.DataClass

import java.io.Serializable

data class Instrument(val name: String,
                      val safe_name: String,
                      val tuning: String) : Serializable {
}

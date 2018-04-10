package com.example.pastkhuf.songbook.DataClass

import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

data class Instrument(val name: String,
                      @PrimaryKey
                      val safe_name: String,
                      val tuning: String) : Serializable {
}

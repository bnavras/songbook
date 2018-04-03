package com.example.pastkhuf.songbook.DataClass

import java.io.Serializable

data class Author (val name: String,
                  val types: ArrayList<String>,
                  val uri: String) :Serializable {
    override fun toString(): String {
        return "$name(${types.joinToString(",")})"
    }
}

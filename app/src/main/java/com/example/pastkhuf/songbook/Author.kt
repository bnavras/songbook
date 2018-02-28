package com.example.pastkhuf.songbook

data class Author(val name: String,
                  val types: ArrayList<String>,
                  val uri: String) {
    override fun toString(): String {
        return "$name(${types.joinToString(",")})"
    }
    class List : ArrayList<Author>()
}

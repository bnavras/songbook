package com.example.pastkhuf.songbook

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.jetbrains.anko.*

class SongListAdapter(private val songs: List<Song>): BaseAdapter() {
    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun getItem(position: Int): Song {
        return songs[position]
    }

    override fun getCount(): Int {
        return songs.size
    }

    @SuppressLint("ResourceType")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val song = getItem(position)

        return  with(parent!!.context){
            relativeLayout {
                val songName = textView {
                    id = 2
                    textSize = 22F
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams{
                    alignParentTop()
                    alignParentLeft()
                    width = parent.width
                }
                val authorsName = textView {
                    id = 3
                    textSize = 15F
                    typeface = Typeface.create("BOLD", Typeface.ITALIC)
                }.lparams{
                    below(2)
                    alignParentRight()
                    width = parent.width
                }
                songName.text = song.title
                authorsName.text = song.authors.joinToString(",")
            }
        }
    }
}
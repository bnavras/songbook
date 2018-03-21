package com.example.pastkhuf.songbook

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.dip
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class SongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val song = intent.getSerializableExtra("song") as Song
        verticalLayout{
            val songName = textView{
                text = song.title
                typeface = Typeface.DEFAULT_BOLD
                width = maxWidth
            }
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(songName,10,30,2, 3)
            textView{
                text = song.body
                setPadding(dip(2), dip(songName.paddingEnd + 20), dip(2), dip(2))
            }
        }
    }
}
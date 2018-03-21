package com.example.pastkhuf.songbook

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class SongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val song = intent.getSerializableExtra("song") as Song
        nestedScrollView{
            verticalLayout{
                textView{
                    topPadding = dip(5)
                    text = song.title
                    textSize = 25f
                    //typeface = Typeface.DEFAULT_BOLD
                    bottomPadding = dip(10)
                }.lparams(width = matchParent)

                textView{
                    text = "Authors:"
                    textSize = 17f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams{/*below(1)*/}

                textView{
                    text = song.authors.joinToString("\n")
                    textSize = 15f
                    bottomPadding = dip(10)
                }.lparams{ /*below(2)*/ }

                textView{
                    textSize = 15f
                    text = song.body
                    setPadding(2,10,2,2)
                }.lparams{ /*below(3)*/ }
            }
        }

    }
}
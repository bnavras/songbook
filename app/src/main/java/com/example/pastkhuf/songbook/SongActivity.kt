package com.example.pastkhuf.songbook

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.pastkhuf.songbook.DataClass.Song
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView

class SongActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val song = intent.getSerializableExtra("song") as Song
        nestedScrollView{
            relativeLayout{
                textView{
                    id = 1
                    topPadding = dip(5)
                    text = song.title
                    textSize = 25f
                    typeface = Typeface.DEFAULT_BOLD
                    bottomPadding = dip(10)
                }.lparams(width = matchParent)

                textView{
                    id = 2
                    text = "Authors:"
                    textSize = 17f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams{below(1)}

                textView{
                    id = 3
                    text = song.authors.joinToString("\n")
                    textSize = 15f
                    bottomPadding = dip(10)
                }.lparams{ below(2) }

                textView{
                    id = 4
                    textSize = 15f
                    text = song.body
                    setPadding(2,10,2,2)
                }.lparams{ below(3) }

                button("Show chords").lparams{
                    alignParentRight()
                }.onClick {
                    doAsync {
                        uiThread {
                            val intent = Intent(this@SongActivity, ChordsActivity::class.java)
                            intent.putExtra("chords", song.chords)
                            startActivity(intent)
                        }
                    }
                }
            }
        }

    }
}
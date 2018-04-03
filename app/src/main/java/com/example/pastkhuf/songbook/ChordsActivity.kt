package com.example.pastkhuf.songbook

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.bumptech.glide.Glide
import com.example.pastkhuf.songbook.DataClass.Chord
import org.jetbrains.anko.imageView
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class ChordsActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chords = intent.getSerializableExtra("chords") as ArrayList<Chord>

        nestedScrollView{
            verticalLayout{
                textView{
                    text = "Аппликатура аккордов"
                    typeface = Typeface.DEFAULT_BOLD
                    textSize = 20f
                    gravity = Gravity.CENTER
                }

                for (chord in chords){
                    val chordView = imageView()
                    Glide
                            .with(this@ChordsActivity)
                            .load(chord.image_url)
                            .into(chordView)
                }
            }
        }
    }
}

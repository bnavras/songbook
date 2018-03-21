package com.example.pastkhuf.songbook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class ChordsActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chords = intent.getSerializableExtra("chords") as ArrayList<Chord>
        verticalLayout{
            textView{
                text = chords[0].name
            }
        }
    }
}

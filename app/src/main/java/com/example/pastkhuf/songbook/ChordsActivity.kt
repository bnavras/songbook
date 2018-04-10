package com.example.pastkhuf.songbook

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.bumptech.glide.Glide
import com.example.pastkhuf.songbook.DataClass.Chord
import database
import org.jetbrains.anko.db.*
import org.jetbrains.anko.imageView
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import android.graphics.Bitmap
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream


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
                    setChord(chordView, chord)
                }
            }
        }
    }
    private fun setChord(chordView: ImageView, chord: Chord){
        val chordFromDb = select(chord.image_url)
        if (false) {    // for debug | chordFromDb == null
            insert(chord.image_url)
        } else {
            Glide
                    .with(this@ChordsActivity)
                    .load(chord.image_url)
                    .into(chordView)
        }
    }
    private fun insert(id: String){
        val img = Picasso.with(this@ChordsActivity)
                         .load(id)
                         .get()

        database.use {
            insert("Chords",
                    "id" to id,
                            "img" to getBitmapAsByteArray(img)
            )
        }
    }
    private fun select(id: String): ByteArray?{
        var chord: ByteArray? = null
        database.use {
            chord = select("Chords")
                    .whereArgs("(id == {id})", "id" to id)
                    .column("img")
                    .exec{ parseOpt(BlobParser) }
        }
        return chord
    }
    private fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray()
    }
}

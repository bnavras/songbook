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
import android.os.Environment
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.nio.file.Files.delete
import java.nio.file.Files.exists
import android.os.Environment.getExternalStorageDirectory
import java.io.File
import java.io.FileOutputStream
import android.graphics.BitmapFactory




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
        var chordImgName = select(chord.image_url)

        if (chordImgName == null) {
            insert(chord.image_url)
            chordImgName = select(chord.image_url)
        }
        chordView.setImageBitmap(getImage(chordImgName))
    }
    private fun insert(id: String){
        val img = Picasso.with(this@ChordsActivity)
                         .load(id)
                         .get()

        database.use {
            insert("Chords",
                    "id" to id,
                            "img" to img
            )
            createDirectoryAndSaveFile(img, id.hashCode().toString())
        }
    }
    private fun select(id: String): String?{
        var chordPath: String? = null
        database.use {
            chordPath = select("Chords")
                    .whereArgs("(id == {id})", "id" to id)
                    .column("path")
                    .exec{ parseOpt(StringParser) }
        }
        return chordPath
    }

    private fun createDirectoryAndSaveFile(imageToSave: Bitmap, fileName: String) {

        val direct = File(Environment.getExternalStorageDirectory().toString() + "/sbchords")

        if (!direct.exists()) {
            val wallpaperDirectory = File("/sdcard/sbchords/")
            wallpaperDirectory.mkdirs()
        }

        val file = File(File("/sdcard/sbchords/"), fileName)
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun getImage(imgName: String?): Bitmap?{
        val imgFile = File("/sdcard/sbchords/" + imgName)
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            return myBitmap
        }
        return null
    }
}

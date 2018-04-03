package com.example.pastkhuf.songbook

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout{
            textView{
                text = "Welcome to Songbook!"
                textSize = 50f
                typeface = Typeface.DEFAULT_BOLD
                padding = 20
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams{
                alignParentTop()
                centerHorizontally()
            }
            val buttonWidth = 800
            val buttonTextSize = 30f
            val buttonColor = Color.argb(100, 26, 72, 235)
            button{
                width = buttonWidth
                id = 1
                text = "Songs"
                textSize = buttonTextSize
            }.lparams{
                centerVertically()
                centerHorizontally()
            }.onClick {
                doAsync {
                    uiThread {
                        val intent = Intent(this@MainActivity, SongsActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            button {
                width = buttonWidth
                id = 2
                text = "Chords"
                textSize = buttonTextSize
            }.lparams{
                below(1)
                centerVertically()
                centerHorizontally()
            }.onClick {

            }

            button {
                width = buttonWidth
                id = 3
                text = "Help"
                textSize = buttonTextSize
                setBackgroundColor(Color.argb(100, 100, 100, 100))
            }.lparams{
                below(2)
                centerVertically()
                centerHorizontally()
            }.onClick {
                doAsync {
                    uiThread {
                        val intent = Intent(this@MainActivity, HelpActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

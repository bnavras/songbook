package com.example.pastkhuf.songbook

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import org.jetbrains.anko.*

class HelpActivity : AppCompatActivity(){
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout{
            textView{
                id = 1
                text = "Songbook"
                typeface = Typeface.DEFAULT_BOLD
                textSize = 20f
                gravity = Gravity.CENTER
            }.lparams{
                centerVertically()
                centerHorizontally()
            }
            textView{
                id = 2
                text = "Developer: Bokiev Navras"
                typeface = Typeface.DEFAULT_BOLD
                textSize = 20f
                gravity = Gravity.CENTER
            }.lparams{
                below(1)
                centerVertically()
                centerHorizontally()
            }

            textView{
                id = 3
                text = "© 2018"
                typeface = Typeface.DEFAULT_BOLD
                textSize = 20f
                gravity = Gravity.CENTER
            }.lparams{
                below(2)
                centerVertically()
                centerHorizontally()
            }
        }
    }
}
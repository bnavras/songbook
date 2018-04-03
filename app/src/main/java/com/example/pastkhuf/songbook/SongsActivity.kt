package com.example.pastkhuf.songbook

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.pastkhuf.songbook.DataClass.Song
import com.example.pastkhuf.songbook.DataClass.SongInfo
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.listView
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.verticalLayout

class SongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout{
            val songsView = listView()

            Thread(Runnable {
                val client = OkHttpClient()
                val request = Request.Builder()
                        .url(resources.getString(R.string.api_path) + "songs/?query=Adam")
                        .addHeader("Guitarparty-Api-Key", resources.getString(R.string.api_key))
                        .build()
                val response = client.newCall(request).execute()
                val responseText = response.body()!!.string()
                val repos = Gson().fromJson(responseText, SongInfo::class.java)

                runOnUiThread{
                    val adapter = SongListAdapter(repos.objects)
                    songsView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }).start()

            songsView.setOnItemClickListener { parent, view, position, l ->
                val song = parent.getItemAtPosition(position) as Song
                doAsync {
                    uiThread {
                        val intent = Intent(this@SongsActivity, SongActivity::class.java)
                        intent.putExtra("song", song)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
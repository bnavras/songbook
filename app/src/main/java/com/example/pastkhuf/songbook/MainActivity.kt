package com.example.pastkhuf.songbook
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout{
            val songsView = listView()

            Thread(Runnable {
                val client = OkHttpClient()
                val request = Request.Builder()
                        .url("http://api.guitarparty.com/v2/songs/?query=Adam")
                        .addHeader("Guitarparty-Api-Key",  "27b65ca0ece5d0cffa3e8fa9c3e3c41c20294f14")
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
                //toast(song.title)
                doAsync {
                    uiThread {
                        val intent = Intent(this@MainActivity, SongActivity::class.java)
                        intent.putExtra("song", song)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

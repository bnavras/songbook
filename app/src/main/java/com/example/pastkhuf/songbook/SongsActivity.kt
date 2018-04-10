package com.example.pastkhuf.songbook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.SearchView
import com.example.pastkhuf.songbook.DataClass.Song
import com.example.pastkhuf.songbook.DataClass.SongInfo
import com.google.gson.Gson
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.*

class SongsActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout{
            val songsView = listView{id = 2}.lparams{below(1)}
            val searcher = searchView{ id = 1 }.lparams(width = matchParent)

            searcher.queryHint = "Enter a query"
            searcher.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextChange(p0: String?): Boolean { return false }
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    loadSongs(songsView, searcher.query)
                    return false
                }
            })
            songsView.setOnItemClickListener { parent, view, position, l ->
                val song = parent.getItemAtPosition(position) as Song
                async(CommonPool) {
                    val intent = Intent(this@SongsActivity, SongActivity::class.java)
                    intent.putExtra("song", song)
                    startActivity(intent)
                }.start()
            }
        }
    }
    private fun loadSongs(songsView: ListView, query: CharSequence) = async(CommonPool) {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(resources.getString(R.string.api_path) + "songs/?query="+ (if(query.isEmpty()) "a" else query))
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
    }.start()
}
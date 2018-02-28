package com.example.pastkhuf.songbook
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this)  //applicationContext
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()


        Thread(Runnable {
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("http://api.guitarparty.com/v2/songs/?query=Jolene")
                    .addHeader("Guitarparty-Api-Key",  "27b65ca0ece5d0cffa3e8fa9c3e3c41c20294f14")
                    .build()
            val response = client.newCall(request).execute()
            val responseText = response.body()!!.string()

            val author = "{\"name\":\"Viktor\", \"types\": [\"Viktor\", \"Viktor\",\"Viktor\"], \"uri\":\"Viktor\"}"
            val ertr = Gson().fromJson(author, Author::class.java)

            val repos = Gson().fromJson(responseText, SongInfo::class.java)
            runOnUiThread{
                val adapter = SongAdapter(repos.objects)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }).start()
    }
}

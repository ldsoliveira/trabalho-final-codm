package com.kleberfh.conversas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RecyclerAdapterChat.OnItemClickListener {

    private val list: ArrayList<ItemChat> = ArrayList()

    private val adapterChat = RecyclerAdapterChat(list, this)

    private val adapterLike = RecyclerAdapterLike(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chatView = findViewById<RecyclerView>(R.id.chat_recycler_view)

        val likeView = findViewById<RecyclerView>(R.id.like_recycler_view)

        val userCount = findViewById<TextView>(R.id.user_count)

        chatView.adapter = adapterChat
        chatView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        chatView.setHasFixedSize(true)

        likeView.adapter = adapterLike
        likeView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        likeView.setHasFixedSize(true)

        Service
            .newClient()
            .getUsers()
            .enqueue(object : Callback<ArrayList<ItemChat>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemChat>>,
                    response: Response<ArrayList<ItemChat>>
                ) {
                    val users = response.body() ?: return;
                    list.addAll(users)
                    adapterChat.notifyDataSetChanged()
                    adapterLike.notifyDataSetChanged()
                    userCount.text = users.size.toString()
                }

                override fun onFailure(call: Call<ArrayList<ItemChat>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    override fun onItemClick(position: Int) {
        changeView(position)
    }

    private fun changeView(position: Int) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("user", Gson().toJson(list[position]))
        startActivity(intent)
    }
}

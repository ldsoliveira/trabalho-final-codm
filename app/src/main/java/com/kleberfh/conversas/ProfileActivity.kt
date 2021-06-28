package com.kleberfh.conversas

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlin.math.roundToInt

class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val user = Gson().fromJson(intent.getStringExtra("user"), ItemChat::class.java)

        val background = findViewById<ImageView>(R.id.background)
        val profileImage = findViewById<ImageView>(R.id.profile)
        val username = findViewById<TextView>(R.id.username)
        val city = findViewById<TextView>(R.id.city)
        val gender = findViewById<TextView>(R.id.gender)
        val sexualOrientation = findViewById<TextView>(R.id.sexual_orientation)
        val age = findViewById<TextView>(R.id.age)

        Glide.with(this).load(user.photo).into(background)
        Glide.with(this).load(user.photo).into(profileImage)

        username.text = user.username
        city.text = user.city
        gender.text = user.gender
        sexualOrientation.text = user.sexualOrientation
        "${user.age.roundToInt()} Anos".also { age.text = it }
    }
}
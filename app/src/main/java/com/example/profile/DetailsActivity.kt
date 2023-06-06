package com.example.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val image :ImageView = findViewById(R.id.profileImage)
        val fname:TextView = findViewById(R.id.firstNameHolder)
        val lname:TextView = findViewById(R.id.lastNameHolder)
        val email:TextView = findViewById(R.id.emailHolder)
        val id:TextView = findViewById(R.id.idHolder)
        val intent = intent
        if (intent.hasExtra("img")){
            //convert to bitmap
            val byteArray = intent.getByteArrayExtra("img")
            fname.text = intent.getStringExtra("fname")
            lname.text = intent.getStringExtra("lname")
            email.text = intent.getStringExtra("email")
            id.text = intent.getIntExtra("id",0).toString()
            var bitmap = byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size) }
            image.setImageBitmap(bitmap)

        }
    }
}
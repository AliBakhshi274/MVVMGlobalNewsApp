package com.example.mvvmglobalnewsapp.ui.no_internet_connection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.ui.MainActivity

class noInternetConnectionActivity : AppCompatActivity() {

    private lateinit var btnTryAgain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_internet_connection_page)

        btnTryAgain = findViewById(R.id.btnTryAgain)

        btnTryAgain.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            this.finish()
        }

    }
}
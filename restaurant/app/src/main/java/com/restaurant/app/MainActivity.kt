package com.restaurant.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_button.setOnClickListener{
            val nextIntent = Intent(this, select_cornerActivity::class.java)
            startActivity(nextIntent)
        }
        login_button.setOnClickListener {
            val nextIntent = Intent(this, loginActivity::class.java)
            startActivity(nextIntent)
        }
    }
}

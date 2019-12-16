package com.student.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener{
            val nextIntent = Intent(this, studentActivity::class.java)
            startActivity(nextIntent)
        }
        signup_button.setOnClickListener{
            val nextIntent = Intent(this, signupActivity::class.java)
            startActivity(nextIntent)
        }
    }
}

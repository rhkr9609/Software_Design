package com.restaurant.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.management_page.*

class ManagementActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.management_page)

        A_Button.setOnClickListener {
            select_corner = "A_menu"
            val nextIntent = Intent(this, menuControlActivity::class.java)
            startActivity(nextIntent)
        }
        B_Button.setOnClickListener {
            select_corner = "B_menu"
            val nextIntent = Intent(this, menuControlActivity::class.java)
            startActivity(nextIntent)
        }
        C_Button.setOnClickListener {
            select_corner = "C_menu"
            val nextIntent = Intent(this, menuControlActivity::class.java)
            startActivity(nextIntent)
        }
        D_Button.setOnClickListener {
            select_corner = "D_menu"
            val nextIntent = Intent(this, menuControlActivity::class.java)
            startActivity(nextIntent)
        }
    }
}


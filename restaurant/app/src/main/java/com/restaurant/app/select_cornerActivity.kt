package com.restaurant.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.select_corner_page.*

var select_corner : String = ""

class select_cornerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_corner_page)

        val nextIntent = Intent(this, cornerActivity::class.java)
        A_corner.setOnClickListener {
            select_corner = "A_order"
            startActivity(nextIntent)
        }
        B_corner.setOnClickListener {
            select_corner = "B_order"
            startActivity(nextIntent)
        }
        C_corner.setOnClickListener {
            select_corner = "C_order"
            startActivity(nextIntent)
        }
        D_corner.setOnClickListener {
            select_corner = "D_order"
            startActivity(nextIntent)
        }
    }
}
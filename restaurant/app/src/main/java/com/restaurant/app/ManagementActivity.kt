package com.restaurant.app

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
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
        A_Button.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                A_Button.setBackgroundResource(R.drawable.aaa)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                A_Button.setBackgroundResource(R.drawable.a)
            }

            false
        })
        B_Button.setOnClickListener {
            select_corner = "B_menu"
            val nextIntent = Intent(this, menuControlActivity::class.java)
            startActivity(nextIntent)
        }
        B_Button.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                B_Button.setBackgroundResource(R.drawable.bbb)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                B_Button.setBackgroundResource(R.drawable.bb)
            }

            false
        })
        C_Button.setOnClickListener {
            select_corner = "C_menu"
            val nextIntent = Intent(this, menuControlActivity::class.java)
            startActivity(nextIntent)
        }
        C_Button.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                C_Button.setBackgroundResource(R.drawable.ccc)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                C_Button.setBackgroundResource(R.drawable.cc)
            }

            false
        })
        D_Button.setOnClickListener {
            select_corner = "D_menu"
            val nextIntent = Intent(this, menuControlActivity::class.java)
            startActivity(nextIntent)
        }
        D_Button.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                D_Button.setBackgroundResource(R.drawable.ddd)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                D_Button.setBackgroundResource(R.drawable.dd)
            }

            false
        })
    }
}


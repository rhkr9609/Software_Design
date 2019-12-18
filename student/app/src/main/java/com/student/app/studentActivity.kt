package com.student.app

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.student_page.*

var select_corner : String = ""

class studentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_page)

        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        btn_balance.setOnClickListener {
            val nextIntent = Intent(this, chargeActivity::class.java)
            startActivity(nextIntent)
        }
        btn_balance.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                btn_balance.setBackgroundResource(R.drawable.chargebutton)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                btn_balance.setBackgroundResource(R.drawable.chargebutton)
            }

            false
        })
=======
        btn_A.setOnClickListener {
            select_corner = "A_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }
        btn_A.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                btn_A.setBackgroundResource(R.drawable.aaa)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                btn_A.setBackgroundResource(R.drawable.a)
            }

            false
        })

        btn_B.setOnClickListener {
            select_corner = "B_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }
        btn_B.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                btn_B.setBackgroundResource(R.drawable.bbb)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                btn_B.setBackgroundResource(R.drawable.bb)
            }

            false
        })

        btn_C.setOnClickListener {
            select_corner = "C_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }

        btn_C.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                btn_C.setBackgroundResource(R.drawable.ccc)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                btn_C.setBackgroundResource(R.drawable.cc)
            }

            false
        })

        btn_D.setOnClickListener {
            select_corner = "D_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }

        btn_D.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                btn_D.setBackgroundResource(R.drawable.ddd)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                btn_D.setBackgroundResource(R.drawable.dd)
            }

            false
        })

        btn_order.setOnClickListener {
            val nextIntent = Intent(this, order_checkActivity::class.java)
            startActivity(nextIntent)
        }
        //databases 호출 부분
        val charge : DatabaseReference = database.getReference("student").child(student.ID).child("money")
        val order : DatabaseReference = database.getReference("student").child(student.ID).child("order")
        charge.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot?.value
                textView3.text = "$value 원"
                student.money = "$value".toInt()
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        order.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val order_state = dataSnapshot?.value
                student.order = "$order_state"
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
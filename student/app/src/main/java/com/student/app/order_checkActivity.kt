package com.student.app

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.order_check.*
import kotlinx.android.synthetic.main.student_page.*


class order_checkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        setContentView(R.layout.order_check)

        button1.setOnClickListener {
            var state = order_state_text.text.toString()
            if(state != "" && state != "조리중" && state != "주문수락대기중") {
                database.reference.child("student").child(student.ID).child("order").setValue("")
                Toast.makeText(this, "수령되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
            else
                Toast.makeText(this, "준비중입니다", Toast.LENGTH_SHORT).show()
        }
        button1.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                button1.setBackgroundResource(R.drawable.getorder2)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                button1.setBackgroundResource(R.drawable.getorder)
            }

            false
        })
        button2.setOnClickListener {
            finish()
        }
        button2.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                button2.setBackgroundResource(R.drawable.confirm2)
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                button2.setBackgroundResource(R.drawable.confirm)
            }

            false
        })
        button2.setOnClickListener {
            finish()
        }
        val orderdata : DatabaseReference = database.getReference("student").child(student.ID).child("order")
        orderdata.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot?.value
                order_state_text.text = "$value"
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
package com.student.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.order_check.*

class order_checkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        setContentView(R.layout.order_check)

        button1.setOnClickListener {

        }
        button2.setOnClickListener {

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
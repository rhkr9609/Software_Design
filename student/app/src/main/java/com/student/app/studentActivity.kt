package com.student.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.student_page.*

object order {
    var studentID: String = student.ID
    var menu: String = ""
    var state: Int = 1
}

var A_num : Int = 0;
var B_num : Int = 0;
var C_num : Int = 0;
var D_num : Int = 0;

class studentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_page)

        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val orderRef = database.getReference()
        btn_balance.setOnClickListener {
            val nextIntent = Intent(this, chargeActivity::class.java)
            startActivity(nextIntent)
        }
        btn_A.setOnClickListener {
            order.menu = "A_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }
        btn_B.setOnClickListener {
            order.menu = "B_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }
        btn_C.setOnClickListener {
            order.menu = "C_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }
        btn_D.setOnClickListener {
            order.menu = "D_menu"
            val nextIntent = Intent(this, cornerActivity::class.java)
            startActivity(nextIntent)
        }
        //databases 호출 부분
        val charge : DatabaseReference = database.getReference("student").child(student.ID).child("money")
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
    }
}
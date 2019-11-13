package com.student.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.charge_page.*

class chargeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.charge_page)

        charge_button.setOnClickListener {
            student.money = moneyText.text.toString().toInt() + student.money
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("student")
            myRef.child(student.ID).child("money").setValue(student.money)
            Toast.makeText(this, "금액충전 성공.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
package com.student.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.signup_page.*

private var firebaseAuth: FirebaseAuth? = null

class signupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        firebaseAuth = FirebaseAuth.getInstance()
        signUp_button2.setOnClickListener{
            createEmail()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val nextIntent = Intent(this, MainActivity::class.java)
        startActivity(nextIntent)
    }
    private fun createEmail(){
        val email = signUp_emailText.text.toString()
        val password = signUp_passwordText.text.toString()
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "email password를 입력하시오", Toast.LENGTH_SHORT).show()
        }
        else {
            firebaseAuth!!.createUserWithEmailAndPassword(
                signUp_emailText.text.toString(),
                signUp_passwordText.text.toString()
            ).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val id = email.split("@")
                    writeStudent(id[0])

                    Toast.makeText(this, "회원가입 성공.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "회원가입 실패 다시 시도하시오.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun writeStudent(ID: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("student")
        student.ID = ID
        student.money = 0
        student.order=""
        myRef.child(ID).setValue(student)
    }
}
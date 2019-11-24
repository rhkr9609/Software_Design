package com.student.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

private var firebaseAuth: FirebaseAuth? = null

object student {
    var ID: String = ""
    var money: Int = 0
    var order: String = ""
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()

        login_button.setOnClickListener{
            loginEmail()
        }
        signup_button.setOnClickListener{
            val nextIntent = Intent(this, signupActivity::class.java)
            startActivity(nextIntent)
        }
    }
    //login function
    private  fun loginEmail(){
        val email = emailText.text.toString()
        val password = passwordText.text.toString()
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "email password를 입력하시오", Toast.LENGTH_SHORT).show()
        }
        else {
            firebaseAuth!!.signInWithEmailAndPassword(
                emailText.text.toString(),
                passwordText.text.toString()
            ).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user_temp = email.split("@")
                    student.ID = user_temp[0]
                    val nextIntent = Intent(this, studentActivity::class.java)
                    startActivity(nextIntent)
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "로그인 실패 다시 시도하시오.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

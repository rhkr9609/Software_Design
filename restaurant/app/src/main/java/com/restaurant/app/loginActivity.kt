package com.restaurant.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.login_button
import kotlinx.android.synthetic.main.login_page.*

open class loginActivity : AppCompatActivity() {

    private val id = "admin"
    private val password = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        login_button.setOnClickListener {
            val inputid = idText.text.trim().toString()
            val inputpassword = passwordText.text.trim().toString()

            if (inputid == id && inputpassword == password) {
                val nextIntent = Intent(this, ManagementActivity::class.java)
                startActivity(nextIntent)
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (inputid.isNullOrEmpty() && inputpassword.isNullOrEmpty()) {
                    Toast.makeText(this, "ID와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
                else if(inputid!=id) {
                    Toast.makeText(this, "존재하지 않는 ID입니다.", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
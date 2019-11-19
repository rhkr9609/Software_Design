package com.student.app


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.student_page.*

class studentActivity : AppCompatActivity() {
    internal lateinit var viewpager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_page)

        btn_balance.setOnClickListener {
            val nextIntent = Intent(this, chargeActivity::class.java)
            startActivity(nextIntent)
        }

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val charge: DatabaseReference =
            database.getReference("student").child(student.ID).child("money")

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


        /*
        val storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference
        val aRef = storageRef.child("A")
         */

        viewpager = findViewById(R.id.pager) as ViewPager
        var adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter
        }
}


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
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.storage.StorageReference
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast

var A_num : Int = 0;
var B_num : Int = 0;
var C_num : Int = 0;
var D_num : Int = 0;



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
        val A_num_ref: DatabaseReference = database.getReference("A_menu_count")
        val B_num_ref: DatabaseReference = database.getReference("B_menu_count")
        val C_num_ref: DatabaseReference = database.getReference("C_menu_count")
        val D_num_ref: DatabaseReference = database.getReference("D_menu_count")
        A_num_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot?.value
                A_num = "$value".toInt()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        B_num_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot?.value
                B_num = "$value".toInt()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        C_num_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot?.value
                C_num = "$value".toInt()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        D_num_ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot?.value
                D_num = "$value".toInt()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        var data:ArrayList<String> = ArrayList()

        val storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference
        var size : Int

        btn_A.setOnClickListener{
            storageRef = storageRef.child("A")
            Toast.makeText(this, "$A_num", Toast.LENGTH_SHORT).show()
        }

        btn_B.setOnClickListener{
            storageRef = storageRef.child("B")
            Toast.makeText(this, "$B_num", Toast.LENGTH_SHORT).show()
        }

        btn_C.setOnClickListener{
            storageRef = storageRef.child("C")
            Toast.makeText(this, "$C_num", Toast.LENGTH_SHORT).show()
        }

        btn_D.setOnClickListener{
            storageRef = storageRef.child("D")
            Toast.makeText(this, "$D_num", Toast.LENGTH_SHORT).show()
        }

        viewpager = findViewById(R.id.pager) as ViewPager
        var adapter = ViewPagerAdapter(this, data)
        viewpager.adapter = adapter
        /*
        val storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference
        val aRef = storageRef.child("A")
         */

        /* viewpager = findViewById(R.id.pager) as ViewPager
         var adapter = ViewPagerAdapter(this)
         viewpager.adapter = adapter*/
    }
}

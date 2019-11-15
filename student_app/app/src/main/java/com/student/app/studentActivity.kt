package com.student.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.charge_page.*
import kotlinx.android.synthetic.main.student_page.*


class studentActivity : AppCompatActivity() {
    var viewList = ArrayList<View>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_page)

        btn_balance.setOnClickListener {
            val nextIntent = Intent(this, chargeActivity::class.java)
            startActivity(nextIntent)
        }

        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
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



        viewList.add(layoutInflater.inflate(R.layout.page_a, null))
        viewList.add(layoutInflater.inflate(R.layout.page_b, null))
        viewList.add(layoutInflater.inflate(R.layout.page_c, null))
        viewList.add(layoutInflater.inflate(R.layout.page_d, null))

        pager.adapter = pagerAdapter()


    }


    inner class pagerAdapter : PagerAdapter()
    {
        override fun getCount(): Int{
            return viewList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var temp = viewList[position]
            pager.addView(temp)
            return viewList[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            pager.removeView(`object` as View)
        }
    }
}



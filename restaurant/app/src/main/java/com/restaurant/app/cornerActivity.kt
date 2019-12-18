package com.restaurant.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.corner_page.*

class cornerActivity : AppCompatActivity(){
    data class User(
        var menuNum: String = "",
        var studentid: String = "",
        var menustate: String = ""
    )

    val start : String = "1"
    val serve : String = "2"
    var ref = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.corner_page)
        val mItem = ArrayList<User>()
        var adapter = ListAdapter(this,mItem)
        listView.adapter = adapter

        ref.child(select_corner).addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                var userdata = User(p0.child("menu").value.toString(),p0.child("studentID").value.toString(),p0.child("state").value.toString())
                mItem.add(userdata)
                adapter.notifyDataSetChanged()
            }
            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }
            override fun onChildRemoved(p0: DataSnapshot) {
                adapter.notifyDataSetChanged()
            }
        })
    }

    inner class ListAdapter(context: Context, item : ArrayList<User>) : BaseAdapter(){

        val mContext = context
        var mitem = item
        val mInflater = LayoutInflater.from(mContext)

        override fun getCount() = mitem.size
        override fun getItem(p0: Int) = mitem[p0]
        override fun getItemId(p0: Int) = p0.toLong()
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
            var view = p1
            if (p1 == null) {
                view = mInflater.inflate(R.layout.row, p2, false)
            }
            var Text: TextView? = view?.findViewById<TextView>(R.id.textView3)
            var button1: Button? = view?.findViewById<Button>(R.id.button1)

            Text?.text = mitem[p0].menuNum
            if(mitem[p0].menustate == start)
                button1!!.text= "승인"
            else
                button1!!.text="제공"
            button1.setOnClickListener {

                var clickbutton = mitem[p0]
                var check = 0
                if(check  == 0 && clickbutton.menustate == start) {
                    button1.text = "제공"
                    mitem[p0].menustate = "2"
                    ref.child(select_corner).child(clickbutton.studentid ).child("state").setValue(2)
                    ref.child("student").child(clickbutton.studentid).child("order").setValue("조리중")
                    check++
                }
                if(check ==0 && clickbutton.menustate == serve)
                {
                    ref.child("student").child(clickbutton.studentid).child("order").setValue(clickbutton.menuNum + " 조리완료")
                    ref.child(select_corner).child(clickbutton.studentid).removeValue()
                    mitem.remove(clickbutton)
                }
                notifyDataSetChanged()
            }
            return view
        }
    }
}
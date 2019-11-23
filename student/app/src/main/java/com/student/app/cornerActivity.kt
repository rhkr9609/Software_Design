package com.student.app

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.corner_page.*
import kotlinx.android.synthetic.main.row.*
import kotlinx.android.synthetic.main.row.textView3
import kotlinx.android.synthetic.main.student_page.*
import java.io.File

class cornerActivity : AppCompatActivity(){
    data class User(
        var form: String = "",
        var name: String = "",
        var price: String = ""
    )

    var data_count : Int = 0
    var ref = FirebaseDatabase.getInstance().reference
    val storage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.corner_page)
        val mItem = ArrayList<User>()
        var adapter = ListAdapter(this,mItem)
        listView.adapter = adapter

        ref.child(order.menu).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(data_count == 0) {
                    Log.d("TAG","dataSnapshot")
                    for (p0 in dataSnapshot.children) {
                        var userdata = User(
                            p0.child("form").value.toString(),
                            p0.child("name").value.toString(),
                            p0.child("price").value.toString()
                        )
                        Log.d("TAG","forLoop")
                        mItem.add(userdata)
                    }
                    adapter.notifyDataSetChanged()
                    data_count = data_count +1
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        /*
        ref.child(order.menu).addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                var userdata = User(p0.child("form").value.toString(),p0.child("name").value.toString(),p0.child("price").value.toString())
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
         */
    }

    private class ViewHolder{
        var image: ImageView? = null
        var Text: TextView? = null
        var button: Button? = null
    }

    inner class ListAdapter(val context: Context, val item : ArrayList<User>) : BaseAdapter(){

        override fun getCount() = item.size
        override fun getItem(p0: Int) = item[p0]
        override fun getItemId(p0: Int) = p0.toLong()
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
            var view : View
            val holder : ViewHolder

            if (p1 == null) {
                view = LayoutInflater.from(context).inflate(R.layout.row, null)
                holder = ViewHolder()
                holder.image = view?.findViewById<ImageView>(R.id.imageView)
                holder.Text = view?.findViewById<TextView>(R.id.textView3)
                holder.button = view?.findViewById<Button>(R.id.button1)
                view.tag = holder
                /* convertView가 null, 즉 최초로 화면을 실행할 때에
                ViewHolder에 각각의 TextView와 ImageView를 findVidwById로 설정.
                마지막에 태그를 holder로 설정한다. */

            } else {
                holder = p1.tag as ViewHolder
                view = p1
                return view
                /* 이미 만들어진 View가 있으므로, tag를 통해 불러와서 대체한다. */
            }
            var mitem = item[p0]
            val test: String = mitem.name + "\n" + mitem.price + "원"
            holder.Text?.text = test
            Log.d("TAG","input text")

            var parent_path = order.menu.split("_")

            val storageRef = storage.reference.child(parent_path[0]).child(mitem.name +"."+mitem.form)
                storageRef.downloadUrl.addOnCompleteListener{
                    task ->  if(task.isSuccessful) {
                         //Log.d("TAG","parent_path : " + parent_path[0])
                         Log.d("TAG","image_name : " + mitem.name +"."+mitem.form)
                         Glide.with(this@cornerActivity).load(task.getResult()).into(holder.image!!)
                     }
            }
            holder.button?.setOnClickListener {

            }
            return view
        }
    }
}
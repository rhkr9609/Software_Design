package com.restaurant.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.menu_control.*
import kotlinx.android.synthetic.main.row2.*

class menuControlActivity : AppCompatActivity(){
    data class User(
        var form: String = "",
        var name: String = "",
        var price: String = ""
    )
    var parent_path = select_corner.split("_")
    var data_count : Int = 0
    var ref = FirebaseDatabase.getInstance().reference
    val storage = FirebaseStorage.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_control)
        val mItem = ArrayList<User>()
        var adapter = ListAdapter(this,mItem)
        listView.adapter = adapter

        addImage_button.setOnClickListener {
            val nextIntent = Intent(this, AddActivity::class.java)
            startActivity(nextIntent)
            finish()
        }

        ref.child(select_corner).addListenerForSingleValueEvent(object : ValueEventListener {
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
                view = LayoutInflater.from(context).inflate(R.layout.row2, null)
                holder = ViewHolder()
                holder.image = view?.findViewById<ImageView>(R.id.imageView)
                holder.Text = view?.findViewById<TextView>(R.id.textView3)
                holder.button = view?.findViewById<Button>(R.id.button)
                view.tag = holder
            } else {
                holder = p1.tag as ViewHolder
                view = p1
                return view
            }
            var mitem = item[p0]

            val test: String = mitem.name + "\n" + mitem.price + "원"
            holder.Text?.text = test

            val storageRef = storage.reference.child(parent_path[0]).child(mitem.name +"."+mitem.form)
            storageRef.downloadUrl.addOnCompleteListener{
                    task ->  if(task.isSuccessful) {
                Glide.with(this@menuControlActivity).load(task.getResult()).into(holder.image!!)
            }
            }
            holder.button?.setOnClickListener {
                storageRef.delete().addOnSuccessListener {
                    ref.child(select_corner).child(mitem.name).removeValue()
                    Toast.makeText(applicationContext,"삭제.",Toast.LENGTH_SHORT).show()
                    val nextIntent = Intent(this@menuControlActivity, menuControlActivity::class.java)
                    startActivity(nextIntent)
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(applicationContext,"실패.",Toast.LENGTH_SHORT).show()
                }

            }
            return view
        }
    }
}
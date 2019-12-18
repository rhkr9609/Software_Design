package com.restaurant.app

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.add_page.*
import java.io.IOException

class AddActivity : AppCompatActivity(),View.OnClickListener {
    private val PICK_IMAGE_REQUEST = 1234
    override fun onClick(p0: View) {
        if(p0 === btnChoose)
            showFileChooser()
        else if(p0 ===btnUpload) {
            if(!nameText.text.isEmpty() && !priceText.text.isEmpty())
                uploadFile()
            else
                Toast.makeText(applicationContext,"이름과 가격을 입력하시오",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK&&data!=null&&data.data!=null)
        {
            filePath = data.data
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                imageView!!.setImageBitmap(bitmap)
            }catch(e:IOException)
            {
                e.printStackTrace()
            }
        }
    }

    private fun uploadFile() {
        if(filePath!=null)
        {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()
            var name = nameText.text.toString()
            var price = priceText.text.toString().toInt()
            val imageRef = storageReference!!.child("A/" + name + ".jpg")
            imageRef.putFile(filePath!!).addOnSuccessListener {
                progressDialog.dismiss()
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference(select_corner)
                myRef.child(name).child("form").setValue("jpg")
                myRef.child(name).child("name").setValue(name)
                myRef.child(name).child("price").setValue(price)
                Toast.makeText(applicationContext,"메뉴추가완료",Toast.LENGTH_SHORT).show()
                val nextIntent = Intent(this, menuControlActivity::class.java)
                startActivity(nextIntent)
            }.addOnFailureListener{
                progressDialog.dismiss()
                Toast.makeText(applicationContext,"실패 다시 시도하시오",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(applicationContext,"이미지를 선택하세요.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"SELECT PICTURE"),PICK_IMAGE_REQUEST)

    }

    private var filePath: Uri? = null
    internal var storage:FirebaseStorage?=null
    internal var storageReference:StorageReference?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_page)

        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        btnChoose.setOnClickListener(this)
        btnUpload.setOnClickListener(this)

    }
}
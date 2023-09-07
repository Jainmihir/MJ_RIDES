package com.hello.mj_riding_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRegistration : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var auth :  FirebaseAuth
    private lateinit var urFirstName : EditText
    private lateinit var urLastName : EditText
    private lateinit var urphone : EditText
    private lateinit var uremail : EditText
    private lateinit var urSubmit :  Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)
        auth = FirebaseAuth.getInstance()
        Toast.makeText(this,"Please Enter Your Details" , Toast.LENGTH_SHORT).show()

        urFirstName = findViewById(R.id.ur_first_name)
        urLastName = findViewById(R.id.ur_last_name)
        uremail = findViewById(R.id.ur_email_id)
        urphone = findViewById(R.id.ur_phone)
        urSubmit = findViewById(R.id.ur_continue)

        if(auth.currentUser!!.phoneNumber !=null ){
            urphone.setText(auth.currentUser!!.phoneNumber)
        }else{
            urphone.text.toString()
        }
        if(auth.currentUser!!.email !=null ){
            uremail.setText(auth.currentUser!!.email)
        }else{
            uremail.text.toString()
        }

        urSubmit.setOnClickListener{
            val FirstName = urFirstName.text.toString()
            val LastName = urLastName.text.toString()
            if(FirstName.isNotEmpty() && LastName.isNotEmpty() && urphone.text.isNotEmpty()
                && uremail.text.isNotEmpty() ){
                database = FirebaseDatabase.getInstance().getReference("User")
                val UserDetails = User(FirstName,LastName,urphone.text.toString(),uremail.text.toString())
                database.child(FirstName).setValue(UserDetails).addOnSuccessListener {
                    uremail.text.clear()
                    urphone.text.clear()
                    urLastName.text.clear()
                    urFirstName.text.clear()
                    Toast.makeText(this@UserRegistration,"Submitted SuccessFully",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,Dashboard::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener{
                    Toast.makeText(this@UserRegistration,"Failed",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@UserRegistration,"Please Fill The all details",Toast.LENGTH_SHORT).show()
            }

        }

    }
}
package com.hello.mj_riding_app


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import driver


class Driver : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var drName: EditText
    private lateinit var drEmail: EditText
    private lateinit var drAadhaar: EditText
    private lateinit var drMob : EditText
    private lateinit var dradress: EditText
    private lateinit var submit: Button
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing the variables
        Toast.makeText(this,"Please Enter Your Details" , Toast.LENGTH_SHORT).show()
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_driver)


//        if(auth.currentUser!!.phoneNumber != null ){
//            drphone.setText(auth.currentUser!!.phoneNumber)
//        } else if(auth.currentUser!!.email != null){
//            drEmail.setText(auth.currentUser!!.email)
//        }
//        var phone : String?= null;
//        var email :  String? = null;
//        if(drphone == null){
//            phone = drphone.text.toString()
//        }else if(drEmail == null){
//            email = drEmail.text.toString()
//        }
        drName = findViewById(R.id.dr_name)
        drEmail = findViewById(R.id.dr_email_id)
        drAadhaar = findViewById(R.id.dr_aadhar_number)
        dradress = findViewById(R.id.dr_address)
        submit = findViewById(R.id.dr_verifyOTPBtn)
        drMob = findViewById(R.id.dr_phone)
//
        database = FirebaseDatabase.getInstance().getReference("driver")
        submit.setOnClickListener {
            if (drName.text.isNotEmpty() && drEmail.text.isNotEmpty() && drAadhaar.text.isNotEmpty()
                && drMob.text.isNotEmpty() && dradress.text.isNotEmpty()) {

                val fullname = drName.text.toString()
                val Address = dradress.text.toString()
                val Aadhar = drAadhaar.text.toString()
                val email = drEmail.text.toString()
                val phone = drMob.text.toString()

                if(Aadhar.length == 12 && phone.length==10 ){
                    val DriverInfo = driver(fullname,Address,Aadhar,phone,email)
                    database.child(Address).setValue(DriverInfo).addOnSuccessListener {
                        drName.text.clear()
                        dradress.text.clear()
                        drAadhaar.text.clear()
                        drEmail.text.clear()
                        Toast.makeText(this, "Submitted SuccessFully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Driver, DriverLogin::class.java)
                        intent.putExtra("phone_number",phone);
                        startActivity(intent)
                        finish()
                        drMob.text.clear()
                    }.addOnFailureListener {
                        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@Driver,"please fill the correct aadhaar number",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@Driver, "please fill the correct details", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null)
        {
            startActivity(Intent(this,Dashboard::class.java))
        }
    }
}
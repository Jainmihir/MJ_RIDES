package com.hello.mj_riding_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Dashboard : AppCompatActivity() {

    private lateinit var BottomNavigation : BottomNavigationView
    private lateinit var toolbar : RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        FirebaseAuth.getInstance()
        replacefragement(Home())
//        toolbar = findViewById(R.id.main_toolbar)
        Toast.makeText(this,"Welcome To MJ Rides" , Toast.LENGTH_SHORT).show()
        BottomNavigation = findViewById(R.id.bottom_navigation)
        BottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.me_home -> {
                    replacefragement(Home())
//                    toolbar.visibility =View.VISIBLE
                }
                R.id.me_profile ->{
                    replacefragement(Profile())
//                    toolbar.visibility = View.INVISIBLE
                }

                else -> {
                }
            }
            true
        }



    }
    private fun replacefragement(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout,fragment)
        fragmentTransaction.commit()
    }
}
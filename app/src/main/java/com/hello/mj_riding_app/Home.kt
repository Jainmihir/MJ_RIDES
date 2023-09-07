package com.hello.mj_riding_app



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth


class Home : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var Bookaride : CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        auth = FirebaseAuth.getInstance()
        Bookaride =view.findViewById(R.id.home_book)
        Bookaride.setOnClickListener{
            val intent = Intent(requireContext(),WhereTo::class.java)
            startActivity(intent)
        }
        return view
    }

}
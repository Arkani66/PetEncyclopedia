package com.example.petencyclopedia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.petencyclopedia.ui.Dog.DogListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var m_textviewaccueil : TextView
    private lateinit var m_buttonaccueil : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        m_textviewaccueil = findViewById(R.id.accueil_text)
        m_buttonaccueil = findViewById(R.id.accueil_button)

        m_buttonaccueil.setOnClickListener {
            Toast.makeText(this@MainActivity, R.string.message_accueil_btn, Toast.LENGTH_LONG)
            .show()
            val intent = Intent(this, Naviguation_Choice::class.java)
            startActivity(intent)
        }
    }

}

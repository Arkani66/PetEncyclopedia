package com.example.petencyclopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var m_textviewaccueil : TextView
    private lateinit var m_buttonaccueil : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        m_textviewaccueil = findViewById(R.id.accueil_text)
        m_buttonaccueil = findViewById(R.id.accueil_button)

        m_buttonaccueil.setOnClickListener (View.OnClickListener{
            Toast.makeText(this@MainActivity, R.string.message_accueil_btn, Toast.LENGTH_LONG)
            .show()
            //startActivity()
        } )
    }

}

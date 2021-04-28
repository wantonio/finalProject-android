package com.example.finalProject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalProject.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigationView)
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        bottomNavigation.visibility = visibility
    }
}
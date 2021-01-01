package com.gv.goodvibse

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Inicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
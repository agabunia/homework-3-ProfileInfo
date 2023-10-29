package com.example.homework_3_profileinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework_3_profileinfo.databinding.NewActivityBinding

class NewActivity : AppCompatActivity() {
    private lateinit var binding: NewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvFullName.text = "${intent.getStringExtra("firstName")!!} ${intent.getStringExtra("lastName")!!}"
        binding.tvEmail.text = intent.getStringExtra("email")!!
        binding.tvUsername.text = intent.getStringExtra("username")!!
        binding.tvAge.text = intent.getStringExtra("age")!!
        binding.tvPhone.text = intent.getStringExtra("phone")!!

        binding.btnAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.serializationdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serializationdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.basicButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BasicFragment())
                .commit()
        }

        binding.retrofitButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RetrofitFragment())
                .commit()
        }
    }
}
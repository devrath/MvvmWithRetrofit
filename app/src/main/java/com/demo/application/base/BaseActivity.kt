package com.demo.application.base

import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.demo.application.databinding.ActivityBaseBinding


open class BaseActivity : AppCompatActivity(){

    private lateinit var binding: ActivityBaseBinding

    override fun setContentView(layoutResID: Int) {
        binding = ActivityBaseBinding.inflate(layoutInflater)
        super.setContentView(binding.root)
    }

}
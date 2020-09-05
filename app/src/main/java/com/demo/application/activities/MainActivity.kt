package com.demo.application.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.application.R
import com.demo.application.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
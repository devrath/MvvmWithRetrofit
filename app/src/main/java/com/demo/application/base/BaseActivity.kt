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

    /**
     * Used to access displaying and hiding the progress
     * @param visible = flag to indicate if the progress
     */
    fun displayProgress(visible: Boolean) {
        if (visible) showProgress() else hideProgress()
    }

    private fun hideProgress() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        binding.progressbar.visibility = View.INVISIBLE
    }

    private fun showProgress() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        binding.progressbar.visibility = View.VISIBLE
    }

}
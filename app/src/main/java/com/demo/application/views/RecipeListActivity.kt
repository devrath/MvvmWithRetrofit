package com.demo.application.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.application.adapters.RecipeRecyclerAdapter
import com.demo.application.base.BaseActivity
import com.demo.application.databinding.ActivityMainBinding
import com.demo.application.viewmodels.RecipeListViewModel


class RecipeListActivity : BaseActivity() {

    private val TAG = "RecipeListActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var mRecipeListViewModel: RecipeListViewModel
    private val usersAdapter = RecipeRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
        subscribeObservers()
        testRetrofitRequest()
        setRecepieList()
    }

    private fun setRecepieList() {
        usersAdapter.apply {
            binding.recipeList.layoutManager = LinearLayoutManager(this@RecipeListActivity)
            binding.recipeList.adapter = usersAdapter
        }
    }

    private fun subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, Observer {
            if(it.isNullOrEmpty()){
               Toast.makeText(this@RecipeListActivity,"Error in retrieving data",Toast.LENGTH_LONG).show()
            }else{
                Log.d("Data",it.toString())
                usersAdapter.updateRecipies(it)
            }
            displayProgress(visible = false)
        })
    }

    private fun testRetrofitRequest() {
        displayProgress(visible = true)
        mRecipeListViewModel.searchRecipesApi("chicken", 1)
    }


    /**
     * Used to access displaying and hiding the progress
     * @param visible = flag to indicate if the progress
     */
    private fun displayProgress(visible: Boolean) {
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
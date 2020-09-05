package com.demo.application.views

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.application.R
import com.demo.application.base.BaseActivity
import com.demo.application.viewmodels.RecipeListViewModel


class RecipeListActivity : BaseActivity() {

    private val TAG = "RecipeListActivity"

    private lateinit var mRecipeListViewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel::class.java)
        subscribeObservers()
        testRetrofitRequest()
    }

    private fun subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, Observer {
            Log.d("Data",it.toString())
        })
    }

    private fun testRetrofitRequest() {
        mRecipeListViewModel.searchRecipesApi("chicken", 1)
    }
}
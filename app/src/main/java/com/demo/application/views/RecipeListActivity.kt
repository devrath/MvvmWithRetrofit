package com.demo.application.views

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.application.R
import com.demo.application.adapters.RecipeRecyclerAdapter
import com.demo.application.base.BaseActivity
import com.demo.application.databinding.ActivityBaseBinding
import com.demo.application.databinding.ActivityMainBinding
import com.demo.application.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_main.*


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
            Log.d("Data",it.toString())
            usersAdapter.updateRecipies(it)
        })
    }

    private fun testRetrofitRequest() {
        mRecipeListViewModel.searchRecipesApi("chicken", 1)
    }
}
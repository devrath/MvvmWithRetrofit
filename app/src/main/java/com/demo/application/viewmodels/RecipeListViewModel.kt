package com.demo.application.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.application.models.Recipe
import com.demo.application.repositories.RecipeRepository


class RecipeListViewModel : ViewModel() {

    fun getRecipes(): LiveData<List<Recipe>> {
        return RecipeRepository.getInstance().getRecipes()
    }

    fun searchRecipesApi(query: String, pageNumber: Int) {
        RecipeRepository.getInstance().searchRecipesApi(query, pageNumber)
    }
}
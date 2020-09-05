package com.demo.application.repositories

import androidx.lifecycle.LiveData
import com.demo.application.models.Recipe
import com.demo.application.requests.apiClientRequests.RecipeApiClient


class RecipeRepository {

    private var recipieApiClient : RecipeApiClient = RecipeApiClient.getInstance()

    companion object {
        private val instance: RecipeRepository = RecipeRepository()
        fun getInstance(): RecipeRepository {
            return instance
        }
    }

    fun getRecipes() : LiveData<List<Recipe>>{
        return recipieApiClient.getRecipes()
    }

    fun searchRecipesApi(query: String, pageNumber: Int) {
        var pageNumber = pageNumber
        if (pageNumber == 0) {
            pageNumber = 1
        }
        RecipeApiClient.getInstance().searchRecipesApi(query, pageNumber)
    }

}
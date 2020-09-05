package com.demo.application.requests.services

import com.demo.application.requests.api.RecipeApi
import com.demo.application.utils.Constants
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory




object ServiceGenerator {
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    private val recipeApi: RecipeApi = retrofit.create(RecipeApi::class.java)

    fun getRecipeApi(): RecipeApi? {
        return recipeApi
    }
}
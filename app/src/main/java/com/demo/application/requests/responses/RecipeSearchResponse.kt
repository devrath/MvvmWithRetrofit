package com.demo.application.requests.responses

import android.os.Parcelable
import com.demo.application.models.Recipe
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeSearchResponse(var count : Int,
                                var recipes: ArrayList<Recipe> = arrayListOf()) : Parcelable
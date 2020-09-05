package com.demo.application.requests.responses

import android.os.Parcelable
import com.demo.application.models.Recipe
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeResponse(var recipe : Recipe) : Parcelable
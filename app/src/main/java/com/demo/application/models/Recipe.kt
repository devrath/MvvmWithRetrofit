package com.demo.application.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(var title : String ,
                  var publisher : String ,
                  var publisher_url : String,
                  var ingredients: ArrayList<Int> = arrayListOf(),
                  var recipe_id : String,
                  var image_url : String,
                  var social_rank : Float) : Parcelable
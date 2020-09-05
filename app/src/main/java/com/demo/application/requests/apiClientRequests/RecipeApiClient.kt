package com.demo.application.requests.apiClientRequests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.application.models.Recipe
import com.demo.application.repositories.RecipeRepository
import com.demo.application.requests.AppExecutors
import com.demo.application.requests.responses.RecipeSearchResponse
import com.demo.application.requests.services.ServiceGenerator.getRecipeApi
import com.demo.application.utils.Constants
import com.demo.application.utils.Constants.NETWORK_TIMEOUT
import retrofit2.Call
import java.util.concurrent.TimeUnit


open class RecipeApiClient {

    private val TAG = "RecipeApiClient"
    private var mRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()

    companion object {
        private val instance: RecipeApiClient = RecipeApiClient()
        fun getInstance(): RecipeApiClient {
            return instance
        }
    }

    fun getRecipes() : LiveData<List<Recipe>> {
        return mRecipes
    }

    fun searchRecipesApi(query: String, pageNumber: Int) {

        val handler = AppExecutors.getInstance().networkIO()?.submit(RetrieveRecipesRunnable(query, pageNumber))

        // Set a timeout for the data refresh
        AppExecutors.getInstance().networkIO()?.schedule({ // let the user know it timed out
            handler?.cancel(true)
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)

    }


    inner  class RetrieveRecipesRunnable(
        private val query: String,
        private val pageNumber: Int
    ) : Runnable {
        private var cancelRequest = false
        override fun run() {
            try {
                val response = getRecipes(query, pageNumber)?.execute()

                if (cancelRequest) {
                    return
                }

                if (response?.code() == 200) {
                    val list: List<Recipe> = ArrayList((response.body() as RecipeSearchResponse).recipes)
                    if (pageNumber == 1) {
                        mRecipes.postValue(list)
                    } else {
                        val currentRecipes = mRecipes.value as MutableList<Recipe>
                        currentRecipes.addAll(list)
                        mRecipes.postValue(currentRecipes)
                    }
                } else {
                    val error: String? = response?.errorBody()?.string()
                    Log.e(TAG, "run: error: $error")
                    mRecipes.postValue(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mRecipes.postValue(null)
            }
        }

        private fun getRecipes(query: String, pageNumber: Int): Call<RecipeSearchResponse?>? {
            return getRecipeApi()!!.searchRecipe(Constants.API_KEY, query, pageNumber.toString())
        }

        private fun cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the retrieval query")
            cancelRequest = true
        }
    }


}
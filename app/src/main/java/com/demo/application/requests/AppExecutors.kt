package com.demo.application.requests

import com.demo.application.requests.apiClientRequests.RecipeApiClient
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors {

    companion object {
        private val instance: AppExecutors = AppExecutors()
        fun getInstance(): AppExecutors {
            return instance
        }
    }

    private val mNetworkIO: ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    fun networkIO(): ScheduledExecutorService? { return mNetworkIO }

}
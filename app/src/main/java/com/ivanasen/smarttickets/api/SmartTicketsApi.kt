package com.ivanasen.smarttickets.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.ivanasen.smarttickets.BuildConfig
import com.ivanasen.smarttickets.models.Event
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SmartTicketsApi {

    @GET("api/events")
    fun getEvents(@Query("order") order: String,
                  @Query("page") page: Int,
                  @Query("limit") limit: Int): Call<List<Event>>

    companion object {
        private val LOG_TAG = SmartTicketsApi::class.simpleName

        val EVENT_ORDER_POPULARITY = "popular"
        val EVENT_ORDER_RECENT = "recent"
        val EVENT_ORDER_OLD = "old"
        val EVENT_PAGE_DEFAULT = 0
        val EVENT_LIMIT_DEFAULT = 10


        val instance: SmartTicketsApi = create()

        private fun create(): SmartTicketsApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d(LOG_TAG, it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            return Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(SmartTicketsApi::class.java)
        }
    }
}
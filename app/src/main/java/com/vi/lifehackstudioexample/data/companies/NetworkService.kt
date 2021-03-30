package com.vi.lifehackstudioexample.data.companies

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class NetworkService {

    companion object {
        const val BASE_URL = "https://lifehack.studio/test_task/"
        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        fun createApi(): Api {
            return retrofit.create(Api::class.java)
        }
    }

}
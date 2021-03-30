package com.vi.lifehackstudioexample.data.companies

import com.vi.lifehackstudioexample.data.companies.pojo.Company
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("test_task/test.php")
    fun getCompanies(): Call<List<Company>>

}
package com.vi.lifehackstudioexample.data.companies

import com.vi.lifehackstudioexample.data.companies.pojo.Company
import com.vi.lifehackstudioexample.data.companies.pojo.DetailCompany
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("test.php")
    fun getCompanies(): Call<List<Company>>

    @GET("test.php?id={id}")
    fun getDetailCompanyById(@Path("id") id: Long): Call<DetailCompany>

}
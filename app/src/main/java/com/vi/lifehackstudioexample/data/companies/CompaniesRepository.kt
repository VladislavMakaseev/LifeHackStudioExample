package com.vi.lifehackstudioexample.data.companies

import com.vi.lifehackstudioexample.domain.companies.CompaniesDataSource
import com.vi.lifehackstudioexample.domain.companies.Company
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.vi.lifehackstudioexample.data.companies.pojo.Company as PojoComany

class CompaniesRepository(
    private val api: Api,
) : CompaniesDataSource {

    override suspend fun getCompanies(): List<Company> {
        val listOfPojoCompanies: List<PojoComany> = suspendCoroutine { coroutine ->
            val call = api.getCompanies()
            call.enqueue(object : Callback<List<PojoComany>> {
                override fun onResponse(
                    call: Call<List<PojoComany>>,
                    response: Response<List<PojoComany>>
                ) {
                    if (response.isSuccessful) {
                        val companies = response.body()
                        Timber.d("kek:: companies = $companies")
                        companies?.let {
                            Timber.d("kek:: response.isSuccessful")
                            coroutine.resume(it)
                        }
                    }
                }

                override fun onFailure(call: Call<List<PojoComany>>, t: Throwable) {
                    Timber.d("kek:: Error $t")
                }
            })
        }
        return listOfPojoCompanies.map {
            Company(
                id = it.id?.toLong(),
                name = it.name,
                img = it.img
            )
        }
    }

    override suspend fun getCompany(id: Long): Company {
        TODO("Not yet implemented")
    }

}
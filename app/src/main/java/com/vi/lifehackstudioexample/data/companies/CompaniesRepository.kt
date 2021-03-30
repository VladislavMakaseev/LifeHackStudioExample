package com.vi.lifehackstudioexample.data.companies

import com.vi.lifehackstudioexample.data.companies.NetworkService.Companion.BASE_URL
import com.vi.lifehackstudioexample.domain.companies.CompaniesDataSource
import com.vi.lifehackstudioexample.domain.companies.Company
import com.vi.lifehackstudioexample.domain.companies.DetailCompany
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.vi.lifehackstudioexample.data.companies.pojo.Company as PojoCompany
import com.vi.lifehackstudioexample.data.companies.pojo.DetailCompany as PojoDetailCompany

class CompaniesRepository(
    private val api: Api,
) : CompaniesDataSource {

    override suspend fun getCompanies(): List<Company> {
        val listOfPojoCompanies: List<PojoCompany> = suspendCoroutine { coroutine ->
            val call = api.getCompanies()
            call.enqueue(object : Callback<List<PojoCompany>> {
                override fun onResponse(
                    call: Call<List<PojoCompany>>,
                    response: Response<List<PojoCompany>>
                ) {
                    if (response.isSuccessful) {
                        val companies = response.body()
                        companies?.let {
                            coroutine.resume(it)
                        }
                    }
                }

                override fun onFailure(call: Call<List<PojoCompany>>, t: Throwable) {
                    Timber.d("kek:: Error $t")
                }
            })
        }

        return listOfPojoCompanies.map {
            Company(
                id = it.id?.toLong(),
                name = it.name,
                img = "${BASE_URL}${it.img}"
            )
        }
    }

    override suspend fun getDetailCompany(id: Long): DetailCompany {
        val call = api.getDetailCompanyById(id)
        val detailPojoCompany: PojoDetailCompany = suspendCoroutine { coroutine ->
            call.enqueue(object : Callback<PojoDetailCompany> {
                override fun onResponse(
                    call: Call<PojoDetailCompany>,
                    response: Response<PojoDetailCompany>
                ) {
                    if (response.isSuccessful) {
                        val detailCompany = response.body()
                        detailCompany?.let {
                            coroutine.resume(it)
                        }
                    }
                }

                override fun onFailure(call: Call<PojoDetailCompany>, t: Throwable) {
                    Timber.d("kek:: Error $t")
                }

            })
        }
        return DetailCompany(
            id = detailPojoCompany.id?.toLong(),
            name = detailPojoCompany.name,
            img = "${BASE_URL}${detailPojoCompany.img}",
            description = detailPojoCompany.description,
            lat = detailPojoCompany.lat,
            lon = detailPojoCompany.lon,
            www = detailPojoCompany.www,
            phone = detailPojoCompany.phone
        )
    }

}
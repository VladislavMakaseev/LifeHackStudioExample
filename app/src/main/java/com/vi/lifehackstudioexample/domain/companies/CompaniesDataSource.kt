package com.vi.lifehackstudioexample.domain.companies

interface CompaniesDataSource {

    suspend fun getCompanies(): List<Company>

    suspend fun getCompany(id: Long): Company

}
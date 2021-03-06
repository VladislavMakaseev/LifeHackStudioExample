package com.vi.lifehackstudioexample.di

import com.vi.lifehackstudioexample.data.companies.CompaniesRepository
import com.vi.lifehackstudioexample.data.companies.NetworkService
import com.vi.lifehackstudioexample.domain.companies.CompaniesDataSource
import com.vi.lifehackstudioexample.domain.companies.GetCompanies
import com.vi.lifehackstudioexample.domain.companies.GetDetailCompany
import com.vi.lifehackstudioexample.presentation.companies.CompaniesViewModel
import com.vi.lifehackstudioexample.presentation.detailcompany.DetailCompanyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val companyModule = module {

    single {
        NetworkService.createApi()
    }

    single<CompaniesDataSource> {
        CompaniesRepository(
            api = get()
        )
    }

    single {
        GetCompanies(
            companiesDataSource = get()
        )
    }

    single {
        GetDetailCompany(
            companiesDataSource = get()
        )
    }

    viewModel {
        CompaniesViewModel(
            getCompanies = get()
        )
    }

    viewModel { (id: Long) ->
        DetailCompanyViewModel(
            id = id,
            getDetailCompany = get()
        )
    }

}
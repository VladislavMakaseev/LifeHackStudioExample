package com.vi.lifehackstudioexample.domain.companies

import com.vi.lifehackstudioexample.domain.InputOutputUseCase

class GetDetailCompany(
    private val companiesDataSource: CompaniesDataSource
) : InputOutputUseCase<GetDetailCompany.Params, DetailCompany> {

    override suspend fun execute(params: Params): DetailCompany {
        return companiesDataSource.getDetailCompany(params.id)
    }

    class Params(
        val id: Long
    )

}
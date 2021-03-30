package com.vi.lifehackstudioexample.domain.companies

import com.vi.lifehackstudioexample.domain.OutputUseCase

class GetCompanies(
    private val companiesDataSource: CompaniesDataSource
) : OutputUseCase<List<Company>> {

    override suspend fun execute(): List<Company> {
        return companiesDataSource.getCompanies()
    }

}
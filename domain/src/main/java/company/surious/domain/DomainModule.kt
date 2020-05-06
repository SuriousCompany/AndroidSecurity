package company.surious.domain

import company.surious.domain.use_cases.GetAccountDataUseCase
import company.surious.domain.use_cases.RefreshTokenUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { RefreshTokenUseCase(get(), get()) }
    factory { GetAccountDataUseCase(get()) }
}
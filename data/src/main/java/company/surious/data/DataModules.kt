package company.surious.data

import com.google.gson.Gson
import company.surious.data.local.LocalDb
import company.surious.data.network.NetworkApi
import company.surious.data.network.NetworkDecoder
import company.surious.data.network.simulator.NetworkEncoderSimulator
import company.surious.data.repositories.AccountRepositoryImpl
import company.surious.data.repositories.AuthRepositoryImpl
import company.surious.data.repositories.PreferencesRepositoryImpl
import company.surious.data.security.AesEncoder
import company.surious.domain.repositories.AccountRepository
import company.surious.domain.repositories.AuthRepository
import company.surious.domain.repositories.PreferencesRepository
import org.koin.dsl.module

val dataModule = module {
    //Repositories:
    single<AccountRepository> { AccountRepositoryImpl(get(), get(), get()) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl() }
    //Network:
    single { NetworkApi(get()) }
    single { NetworkDecoder(get(), get()) }
    //Local:
    single { LocalDb.getInstance(get()) }
    //Simulator:
    single { NetworkEncoderSimulator(get()) }
    //Security:
    single { AesEncoder(Gson()) }
}

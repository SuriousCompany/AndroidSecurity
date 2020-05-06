package company.surious.data.repositories

import company.surious.data.network.NetworkApi
import company.surious.data.network.NetworkDecoder
import company.surious.domain.entities.AccountData
import company.surious.domain.repositories.AccountRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class AccountRepositoryImpl(
    private val networkApi: NetworkApi,
    private val networkDecoder: NetworkDecoder
) : AccountRepository {

    override fun getAccountData(): Single<AccountData> =
        Single.zip(
            networkApi.getAccountData(),
            networkApi.getCards(),
            BiFunction<String, String, AccountData> { accountResponse: String, cardsResponse: String ->
                networkDecoder.decode(accountResponse, cardsResponse)
            })
}
package company.surious.data.repositories

import android.util.Log
import company.surious.data.local.LocalDb
import company.surious.data.local.LocalMapper
import company.surious.data.local.entities.CompositeAccountRoomEntity
import company.surious.data.network.NetworkApi
import company.surious.data.network.NetworkDecoder
import company.surious.domain.entities.AccountData
import company.surious.domain.repositories.AccountRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class AccountRepositoryImpl(
    private val networkApi: NetworkApi,
    private val localDb: LocalDb,
    private val networkDecoder: NetworkDecoder
) : AccountRepository {

    override fun getAccountData(): Single<AccountData> =
        requestLocalData().flatMap {
            if (it.isNotEmpty()) {
                Log.i("pizda1", "Has local data. Return it.")
                Single.just(LocalMapper.map(it.first()))
            } else {
                Log.i("pizda1", "No local data.")
                requestNetworkDataAndCache()
            }
        }


    private fun requestNetworkDataAndCache() =
        Single.create<AccountData> { emitter ->
            Log.i("pizda1", "Requesting network data.")
            requestFullAccountData().map(::decode).subscribe(
                { account ->
                    Log.i("pizda1", "Received account from network. Saving it to the local db.")
                    val localAccount = LocalMapper.map(account)
                    localDb.accountDao().update(localAccount).subscribe(
                        {
                            if (!emitter.isDisposed) {
                                Log.i("pizda1", "Account saved")
                                emitter.onSuccess(account)
                            }
                        },
                        { error ->
                            if (!emitter.isDisposed) {
                                emitter.onError(error)
                            }
                        }
                    )
                },
                { error ->
                    if (!emitter.isDisposed) {
                        emitter.onError(error)
                    }
                }
            )
        }


    private fun requestLocalData(): Single<List<CompositeAccountRoomEntity>> =
        localDb.accountDao().getAll()

    private fun requestFullAccountData(): Single<Pair<String, String>> =
        Single.zip(
            networkApi.getAccountData(),
            networkApi.getCards(),
            BiFunction<String, String, Pair<String, String>>
            { accountResponse: String, cardsResponse: String ->
                Pair(accountResponse, cardsResponse)
            })

    private fun decode(accountResponse: Pair<String, String>) =
        networkDecoder.decode(accountResponse.first, accountResponse.second)

}
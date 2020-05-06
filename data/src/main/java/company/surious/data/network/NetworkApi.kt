package company.surious.data.network

import company.surious.data.network.simulator.AccountGenerator
import company.surious.data.network.simulator.NetworkEncoderSimulator
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class NetworkApi(private val networkEncoderSimulator: NetworkEncoderSimulator) {

    fun getAccountData() =
        Single.just(networkEncoderSimulator.encryptAccount(AccountGenerator.generateAccount()))
            .delay(5, TimeUnit.SECONDS)

    fun getCards() =
        Single.just(networkEncoderSimulator.encryptCards(AccountGenerator.generateCards()))
            .delay(5, TimeUnit.SECONDS)
}
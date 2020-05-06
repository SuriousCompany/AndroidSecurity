package company.surious.data.network.simulator

import company.surious.data.network.entities.NetworkAccountData
import company.surious.data.network.entities.NetworkCard
import company.surious.data.security.AesEncoder

class NetworkEncoderSimulator(private val aesEncoder: AesEncoder) {
    companion object {
        const val SERVER_KEY = "DAUNDAUNDAUNDAUN"
    }

    fun encryptAccount(networkAccountData: NetworkAccountData) =
        aesEncoder.encrypt(networkAccountData, SERVER_KEY)

    fun encryptCards(networkCards: List<NetworkCard>) =
        aesEncoder.encrypt(networkCards, SERVER_KEY)


}
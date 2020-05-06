package company.surious.data.network

import com.google.gson.reflect.TypeToken
import company.surious.data.network.entities.NetworkAccountData
import company.surious.data.network.entities.NetworkCard
import company.surious.data.security.AesEncoder
import company.surious.domain.entities.AccountData
import company.surious.domain.repositories.PreferencesRepository

class NetworkDecoder(
    private val aesEncoder: AesEncoder,
    private val preferencesRepository: PreferencesRepository
) {
    init {
        System.loadLibrary("temp")
    }

    private external fun temp(): String

    fun decode(accountResponse: String, cardsResponse: String): AccountData {
        val decodedNetworkKey = preferencesRepository.networkKey!!
        val networkAccount =
            aesEncoder.decrypt<NetworkAccountData>(
                accountResponse,
                NetworkAccountData::class.java,
                decodedNetworkKey
            )
        val networkCards = aesEncoder.decrypt<List<NetworkCard>>(
            cardsResponse,
            object : TypeToken<List<NetworkCard>>() {}.type, decodedNetworkKey
        )
        return NetworkMapper.map(networkAccount, networkCards)
    }
}
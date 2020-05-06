package company.surious.data.network

import company.surious.data.network.entities.NetworkAccountData
import company.surious.data.network.entities.NetworkCard
import company.surious.domain.entities.AccountData
import company.surious.domain.entities.Card

object NetworkMapper {
    fun map(networkAccountData: NetworkAccountData, networkCards: List<NetworkCard>) =
        with(networkAccountData) {
            AccountData(
                accountId, firstName, lastName, mapCards(networkCards)
            )
        }

    private fun mapCards(networkCards: List<NetworkCard>): List<Card> =
        networkCards.map {
            with(it) {
                Card(cardId, cardNumber, expirationDate, cardName)
            }
        }
}
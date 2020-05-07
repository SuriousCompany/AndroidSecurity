package company.surious.data.local

import company.surious.data.local.entities.AccountRoomEntity
import company.surious.data.local.entities.CardRoomEntity
import company.surious.data.local.entities.CompositeAccountRoomEntity
import company.surious.domain.entities.AccountData
import company.surious.domain.entities.Card

object LocalMapper {
    fun map(account: AccountData) =
        with(account) {
            CompositeAccountRoomEntity(
                mapAccountData(this),
                mapCards(cards, accountId)
            )
        }

    private fun mapAccountData(account: AccountData) =
        with(account) {
            AccountRoomEntity(
                accountId, firstName, lastName
            )
        }

    private fun mapCards(cards: List<Card>, accountId: Long) =
        cards.map {
            with(it) {
                CardRoomEntity(
                    cardId, cardNumber, expirationDate, cardName, accountId
                )
            }
        }

    fun map(account: CompositeAccountRoomEntity) =
        with(account.accountRoomEntity) {
            AccountData(
                accountId, firstName, lastName,
                mapLocalCards(account.cards)
            )
        }

    private fun mapLocalCards(cards: List<CardRoomEntity>) =
        cards.map {
            with(it) {
                Card(cardId, cardNumber, expirationDate, cardName)
            }
        }

}
package company.surious.domain.entities

data class Card(
    val cardId: Long,
    val cardNumber: Long,
    val expirationDate: Long,
    val cardName: String
)
package company.surious.data.network.entities

data class NetworkCard(
    val cardId: Long,
    val cardNumber: Long,
    val expirationDate: Long,
    val cardName: String
)
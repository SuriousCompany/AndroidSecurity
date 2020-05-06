package company.surious.domain.entities

data class AccountData(
    val accountId: Long = -1,
    val firstName: String = "",
    val lastName: String = "",
    val cards: List<Card> = ArrayList()
)

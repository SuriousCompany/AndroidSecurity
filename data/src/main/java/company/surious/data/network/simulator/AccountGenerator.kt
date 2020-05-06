package company.surious.data.network.simulator

import company.surious.data.network.entities.NetworkAccountData
import company.surious.data.network.entities.NetworkCard
import kotlin.random.Random

object AccountGenerator {
    private val random = Random(System.currentTimeMillis())
    private const val MAX_CARD_NUMBER = 9999999999999999

    fun generateAccount() = NetworkAccountData(
        1,
        "vsevolod",
        "salenko"
    )

    fun generateCards() = listOf(
        generateCard(
            1488
        ),
        generateCard(228),
        generateCard(1337)
    )

    private fun generateCard(id: Long) =
        NetworkCard(
            id,
            random.nextLong(
                MAX_CARD_NUMBER
            ),
            System.currentTimeMillis() + random.nextLong(),
            "Card $id"
        )
}
package company.surious.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CompositeAccountRoomEntity(
    @Embedded
    val accountRoomEntity: AccountRoomEntity,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "accountId"
    )
    var cards: List<CardRoomEntity>
)
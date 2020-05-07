package company.surious.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity
data class CardRoomEntity(
    @PrimaryKey
    val cardId: Long,
    val cardNumber: Long,
    val expirationDate: Long,
    val cardName: String,
    @ForeignKey(
        entity = AccountRoomEntity::class,
        parentColumns = ["accountId"],
        childColumns = ["accountId"],
        onDelete = CASCADE
    )
    val accountId: Long
)
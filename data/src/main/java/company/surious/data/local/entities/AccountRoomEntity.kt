package company.surious.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountRoomEntity(
    @PrimaryKey
    val accountId: Long = -1,
    val firstName: String = "",
    val lastName: String = ""
)
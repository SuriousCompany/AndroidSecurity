package company.surious.data.local.daos

import androidx.room.*
import company.surious.data.local.entities.AccountRoomEntity
import company.surious.data.local.entities.CardRoomEntity
import company.surious.data.local.entities.CompositeAccountRoomEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class AccountRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAccount(accountRoomEntity: AccountRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCards(cards: List<CardRoomEntity>)

    @Transaction
    @Query("SELECT * FROM AccountRoomEntity")
    abstract fun getAll(): Single<List<CompositeAccountRoomEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(compositeAccountRoomEntity: CompositeAccountRoomEntity) = Completable.fromAction {
        with(compositeAccountRoomEntity) {
            insertAccount(accountRoomEntity)
            insertCards(cards)
        }
    }
}
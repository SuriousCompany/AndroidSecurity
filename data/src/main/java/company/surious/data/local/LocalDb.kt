package company.surious.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amitshekhar.DebugDB
import company.surious.data.BuildConfig
import company.surious.data.local.daos.AccountRoomDao
import company.surious.data.local.entities.AccountRoomEntity
import company.surious.data.local.entities.CardRoomEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@Database(
    entities = [AccountRoomEntity::class, CardRoomEntity::class],
    version = 1
)
abstract class LocalDb : RoomDatabase() {

    init {
        System.loadLibrary("temp")
    }

    abstract fun accountDao(): AccountRoomDao

    companion object {
        private external fun temp(): String
        private val passphrase = SQLiteDatabase.getBytes(temp().toCharArray())


        @Volatile
        private var INSTANCE: LocalDb? = null

        fun getInstance(context: Context): LocalDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): LocalDb {
            if (BuildConfig.DEBUG) {
                Log.i("pizda1", DebugDB.getAddressLog())
            }
            val factory = SupportFactory(passphrase)
            return Room.databaseBuilder(
                context.applicationContext,
                LocalDb::class.java, "encrypted.db"
            ).openHelperFactory(factory).build()
        }
    }
}
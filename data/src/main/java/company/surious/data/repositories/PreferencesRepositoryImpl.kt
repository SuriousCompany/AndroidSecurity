package company.surious.data.repositories

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import company.surious.domain.repositories.PreferencesRepository

class PreferencesRepositoryImpl(applicationContext: Context) : PreferencesRepository {
    companion object {
        private const val KEY = "key"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "prefs",
        masterKeyAlias,
        applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    override var networkKey: String?
        get() = sharedPreferences.getString(KEY, null)
        set(value) {
            sharedPreferences.edit().putString(KEY, value).apply()
        }
}
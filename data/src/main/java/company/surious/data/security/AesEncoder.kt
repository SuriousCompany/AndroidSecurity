package company.surious.data.security

import android.util.Base64
import com.google.gson.Gson
import java.lang.reflect.Type
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AesEncoder(private val gson: Gson) {
    companion object {
        private const val AES = "AES"
        private const val TRANSFORMATION = "AES/CBC/PKCS5Padding"
    }

    init {
        System.loadLibrary("temp")
    }

    private external fun vector(): String

    fun <T> encrypt(data: T, key: String): String {
        val keyBytes: ByteArray = key.toByteArray()
        val ivBytes: ByteArray = vector().toByteArray()
        val secretKeySpec = SecretKeySpec(keyBytes, AES)
        val cipher: Cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(ivBytes))
        val encoded = cipher.doFinal(gson.toJson(data).toByteArray())
        return String(Base64.encode(encoded, Base64.DEFAULT))
    }

    fun <T> decrypt(data: String, dataType: Type, key: String): T {
        val keyBytes: ByteArray = key.toByteArray()
        val ivBytes: ByteArray = vector().toByteArray()
        val encryptedBytes: ByteArray = Base64.decode(data, Base64.DEFAULT)
        val secretKeySpec = SecretKeySpec(keyBytes, AES)
        val cipher: Cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(ivBytes))
        val decryptedJson = String(cipher.doFinal(encryptedBytes))
        return gson.fromJson(decryptedJson, dataType)
    }
}
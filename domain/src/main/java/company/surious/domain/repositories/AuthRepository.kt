package company.surious.domain.repositories

import io.reactivex.Single

interface AuthRepository {
    fun refreshToken(): Single<String>
}
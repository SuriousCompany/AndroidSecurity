package company.surious.data.repositories

import company.surious.data.network.simulator.NetworkEncoderSimulator
import company.surious.domain.repositories.AuthRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class AuthRepositoryImpl :
    AuthRepository {
    override fun refreshToken(): Single<String> =
        Single.just(NetworkEncoderSimulator.SERVER_KEY).delay(5, TimeUnit.SECONDS)
}
package company.surious.domain.use_cases

import company.surious.domain.repositories.AuthRepository
import company.surious.domain.repositories.PreferencesRepository
import company.surious.domain.use_cases.base.CompletableUseCase
import io.reactivex.Completable

class RefreshTokenUseCase(
    authRepository: AuthRepository,
    preferencesRepository: PreferencesRepository
) : CompletableUseCase<Void?>() {

    override val source: Completable = authRepository.refreshToken().map {
        preferencesRepository.networkKey = it
        it
    }.ignoreElement()
}
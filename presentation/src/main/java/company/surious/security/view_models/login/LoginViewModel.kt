package company.surious.security.view_models.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import company.surious.domain.repositories.PreferencesRepository
import company.surious.domain.use_cases.RefreshTokenUseCase
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val disposables = CompositeDisposable()

    val loginStateLiveData = MutableLiveData<LoginState>().apply {
        value =
            if (preferencesRepository.networkKey == null) {
                LoginState.EMPTY
            } else {
                LoginState.LOGIN
            }
    }

    fun login() {
        disposables.add(
            refreshTokenUseCase.execute(null).subscribe(
                {
                    loginStateLiveData.value = LoginState.LOGIN
                },
                { error ->
                    loginStateLiveData.value = LoginState.ERROR
                    Log.e("pizda1", "login", error)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
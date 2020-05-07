package company.surious.security.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import company.surious.domain.entities.AccountData
import company.surious.domain.use_cases.GetAccountDataUseCase
import io.reactivex.disposables.CompositeDisposable

class AccountViewModel(private val getAccountDataUseCase: GetAccountDataUseCase) : ViewModel() {
    private val disposables = CompositeDisposable()
    fun update() {
        Log.i("pizda1", "updating account data")
        disposables.add(getAccountDataUseCase.execute(null).subscribe(
            { account ->
                displayAccount(account)
            },
            { error ->
                Log.i("pizda1", "account", error)
            }
        ))
    }

    private fun displayAccount(accountData: AccountData) {
        Log.i("pizda1", "account ${accountData.firstName} ${accountData.lastName} displayed")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
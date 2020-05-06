package company.surious.domain.repositories

import company.surious.domain.entities.AccountData
import io.reactivex.Single

interface AccountRepository {
    fun getAccountData(): Single<AccountData>
}
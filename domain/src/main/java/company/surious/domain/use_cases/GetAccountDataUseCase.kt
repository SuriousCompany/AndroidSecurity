package company.surious.domain.use_cases

import company.surious.domain.entities.AccountData
import company.surious.domain.repositories.AccountRepository
import company.surious.domain.use_cases.base.SingleUseCase
import io.reactivex.Single

class GetAccountDataUseCase(accountRepository: AccountRepository) :
    SingleUseCase<Void?, AccountData>() {
    override val source: Single<AccountData> = accountRepository.getAccountData()
}
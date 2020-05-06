package company.surious.domain.use_cases.base

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<Request> {
    abstract val source: Completable

    fun execute(request: Request) =
        source.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
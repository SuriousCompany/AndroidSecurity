package company.surious.domain.use_cases.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Request, Response> {
    fun execute(request: Request) =
        source.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    abstract val source: Single<Response>
}
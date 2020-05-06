package company.surious.security.view_models

import company.surious.security.view_models.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AccountViewModel(get()) }
    viewModel {
        LoginViewModel(
            get(),
            get()
        )
    }
}
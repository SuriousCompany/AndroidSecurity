package company.surious.security

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import company.surious.security.databinding.ActivityMainBinding
import company.surious.security.view_models.AccountViewModel
import company.surious.security.view_models.login.LoginState
import company.surious.security.view_models.login.LoginViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by inject()
    private val accountViewModel: AccountViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        loginViewModel.loginStateLiveData.observe(this, Observer {
            when (loginViewModel.loginStateLiveData.value) {
                LoginState.EMPTY -> loginViewModel.login()
                LoginState.LOGIN -> onSuccessfulLogin()
                LoginState.ERROR -> showErrorToast()
            }
        })
    }

    private fun showErrorToast() {
        Toast.makeText(
            this@MainActivity,
            "Login error",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onSuccessfulLogin() {
        accountViewModel.update()
    }
}

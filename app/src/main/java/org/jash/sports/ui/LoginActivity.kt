package org.jash.sports.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.sports.R
import org.jash.sports.databinding.ActivityLoginBinding
import org.jash.sports.entry.User
import org.jash.sports.viewmodel.LoginViewModel

@Route(path = "/news/login")
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    val user by lazy { User(username = "user") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.user = user
        binding.login.setOnClickListener {
            viewModel.login(user)
        }
        binding.registry.setOnClickListener {
            ARouter.getInstance().build("/news/check_phone")
                .navigation()
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}
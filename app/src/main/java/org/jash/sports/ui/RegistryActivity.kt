package org.jash.sports.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.sports.R
import org.jash.sports.databinding.ActivityRegistryBinding
import org.jash.sports.entry.User
import org.jash.sports.viewmodel.RegistryViewModel

@Route(path = "/news/registry")
class RegistryActivity : BaseActivity<ActivityRegistryBinding, RegistryViewModel>() {
    @Autowired
    @JvmField
    var phone = ""
    val user by lazy { User(phone = phone) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        binding.user = user
        binding.confirm.editText?.addTextChangedListener {
            if (user.password != binding.confirm.editText?.text.toString()) {
                binding.confirm.isErrorEnabled = true
                binding.confirm.error = "两次密码不一致"
            } else {
                binding.confirm.isErrorEnabled = false
            }
        }
        binding.registry.setOnClickListener {
            viewModel.registry(user)
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    fun registried(flag:Boolean) {
        //关闭任务栈
        finishAffinity()
    }
}
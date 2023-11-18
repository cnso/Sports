package org.jash.sports.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.sports.R
import org.jash.sports.databinding.ActivityCheckPhoneBinding
import org.jash.sports.entry.Sign
import org.jash.sports.viewmodel.CheckPhoneViewModel

@Route(path = "/news/check_phone")
class CheckPhoneActivity : BaseActivity<ActivityCheckPhoneBinding, CheckPhoneViewModel>() {
    val sign by lazy { Sign() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.sign = sign
        binding.getCode.setOnClickListener {
            viewModel.getCode(sign.phone)
        }
        binding.signBtn.setOnClickListener {
            viewModel.sign(sign)
        }
        binding.phone.editText?.addTextChangedListener {
            if (sign.phone.length == 11) {
                binding.phone.isErrorEnabled = false
            } else {
                binding.phone.error = "手机号非法"
                binding.phone.isErrorEnabled = true
            }
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    fun loadedCode(code:Int) {
        sign.code = code.toString()
    }

    fun signed(flag:Boolean) {
        ARouter.getInstance().build("/news/registry")
            .withString("phone", sign.phone)
            .navigation()

    }
}
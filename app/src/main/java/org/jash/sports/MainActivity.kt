package org.jash.sports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import org.jash.common.mvvm.BaseActivity
import org.jash.sports.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
//    val binding:ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
//    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tv.text = "开始"
        viewModel.progressLiveData.observe(this) {
            progress(it)
        }
        viewModel.completeLiveData.observe(this) {
            complete(it)
        }
        viewModel.start()
    }
    fun progress(p:Int) {
        binding.tv.text = "倒计时 $p 秒"
    }
    fun complete(s:String) {
        binding.tv.text = s
    }
}
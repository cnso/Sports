package org.jash.sports.ui

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.common.utils.log
import org.jash.sports.viewmodel.MainViewModel
import org.jash.sports.databinding.ActivityMainBinding
import org.jash.sports.entry.Category

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
//    val binding:ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
//    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tv.text = "开始"
//        viewModel.progressLiveData.observe(this) {
//            progress(it)
//        }
//        viewModel.completeLiveData.observe(this) {
//            complete(it)
//        }
        viewModel.start()
        viewModel.loadCategory()
    }
    fun progress(p:Int) {
        binding.tv.text = "倒计时 $p 秒"
    }
    fun complete(s:String) {
        binding.tv.text = s
        ARouter.getInstance().build("/news/home").navigation()
        finish()
    }
    fun loaded(category: List<Category>) {
        log("加载成功")
        log("data: $category")
    }
}
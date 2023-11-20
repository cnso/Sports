package org.jash.sports.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.common.utils.token
import org.jash.sports.dao.database
import org.jash.sports.databinding.ActivityDetailBinding
import org.jash.sports.entry.News
import org.jash.sports.viewmodel.DetailViewModel

@Route(path = "/news/detail")
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    @Autowired
    @JvmField
    var id:Int = 0
    lateinit var ids:List<Int>
    lateinit var news: News
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.loadNews(id)
        if (token != null) {
            viewModel.loadCollect()
            binding.collect.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked != id in ids) {
                    viewModel.collect(id)
                    if (isChecked) {
                        ids += id
                        news.collects += 1
                    } else {
                        ids -= id
                        news.collects -= 1
                    }
                }
             }
            binding.commentText.setImeActionLabel("发送", 0)
            binding.commentText.setOnEditorActionListener { v, actionId, event ->
                if (v.text.isEmpty()) {
                    Toast.makeText(this, "不能发空消息", Toast.LENGTH_SHORT).show()
                    true
                } else {
                    Toast.makeText(this, "发送消息:${v.text}", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        } else {
            binding.commentText.isEnabled = false
            binding.collect.isEnabled = false
        }
    }

    fun loaded(news:News) {
        this.news = news
        binding.news = news
        title = news.title
    }
    fun collect(ids:List<Int>) {
        this.ids = ids
        binding.collect.isChecked = id in ids
    }
    fun collect(str:String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { DetailViewModel(database.getNewsDao()) } }
}
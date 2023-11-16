package org.jash.sports.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.sports.dao.database
import org.jash.sports.databinding.ActivityDetailBinding
import org.jash.sports.entry.News
import org.jash.sports.viewmodel.DetailViewModel

@Route(path = "/news/detail")
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {
    @Autowired
    @JvmField
    var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.loadNews(id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    fun loaded(news:News) {
        binding.news = news
        title = news.title
    }

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { DetailViewModel(database.getNewsDao()) } }
}
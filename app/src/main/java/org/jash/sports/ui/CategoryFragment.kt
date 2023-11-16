package org.jash.sports.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.adapter.CommonAdapter
import org.jash.common.mvvm.BaseFragment
import org.jash.sports.R
import org.jash.sports.BR
import org.jash.sports.dao.database
import org.jash.sports.databinding.FragmentCategoryBinding
import org.jash.sports.entry.Category
import org.jash.sports.entry.News
import org.jash.sports.entry.Page
import org.jash.sports.viewmodel.CategoryViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Route(path = "/news/category")
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>(){
    @Autowired
    @JvmField
    var id = 0
    val adapter by lazy { CommonAdapter<News>({ R.layout.item_news to BR.news}) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        viewModel.loadPage(id, 1, 5)
    }
    fun loadedPage(page:Page<News>) {
        adapter += page.records
    }
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactory { initializer { CategoryViewModel(requireContext().database.getNewsDao()) } }

}
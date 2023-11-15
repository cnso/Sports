package org.jash.sports.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import org.jash.common.mvvm.BaseFragment
import org.jash.sports.PagerAdapter
import org.jash.sports.R
import org.jash.sports.dao.database
import org.jash.sports.databinding.FragmentNewsBinding
import org.jash.sports.entry.Category
import org.jash.sports.viewmodel.NewsFViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Route(path = "/news/news")
class NewsFragment : BaseFragment<FragmentNewsBinding, NewsFViewModel>() {
    private val adapter by lazy { PagerAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.searchBar)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }.attach()
        viewModel.loadCategory()

//        Snackbar.make(binding.root, "新的 toast", Snackbar.LENGTH_LONG)
//            .setAction("取消") {
//
//            }
//            .show()
    }
    fun loaded(categories: List<Category>) {
        adapter += categories
    }

}
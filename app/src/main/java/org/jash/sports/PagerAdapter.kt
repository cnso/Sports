package org.jash.sports

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.sports.entry.Category
import org.jash.sports.ui.CategoryFragment

class PagerAdapter(fragment: Fragment, val data:MutableList<Category> = mutableListOf()):FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment = ARouter.getInstance().build("/news/category")
        .withString("name", data[position].name).navigation() as Fragment
}
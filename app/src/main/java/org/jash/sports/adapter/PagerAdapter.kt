package org.jash.sports.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.sports.entry.Category

class PagerAdapter(fragment: Fragment, val data:MutableList<Category> = mutableListOf()):FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment = ARouter.getInstance().build("/news/category")
        .withInt("id", data[position].id).navigation() as Fragment

    operator fun plusAssign( categories: List<Category>) {
//        notifyDataSetChanged()
        val size = data.size
        data += categories
        notifyItemRangeInserted(size, categories.size)
    }
    fun getTitle(position: Int):String {
        return data[position].name
    }
}
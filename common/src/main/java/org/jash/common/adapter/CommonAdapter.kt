package org.jash.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CommonAdapter<D>(val map:(d:D) -> Pair<Int, Int>, val data:MutableList<D> = mutableListOf()):Adapter<CommonViewHolder>() {
    override fun getItemViewType(position: Int): Int = map(data[position]).first

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder =
        CommonViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.binding.setVariable(map(data[position]).second, data[position])
    }
    operator fun plusAssign(list:List<D>) {
        val size = data.size
        data += list
        notifyItemRangeInserted(size, list.size)
    }
}
class CommonViewHolder(val binding:ViewDataBinding):ViewHolder(binding.root)
package org.jash.sports.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import org.jash.common.mvvm.BaseFragment
import org.jash.common.utils.token
import org.jash.sports.R
import org.jash.sports.databinding.FragmentMineBinding
import org.jash.sports.entry.User
import org.jash.sports.viewmodel.MineViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Route(path = "/news/mine")
class MineFragment : BaseFragment<FragmentMineBinding, MineViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetail()
        binding.logout.setOnClickListener {
            viewModel.logout()
        }
    }
    fun loadDetail(user:User) {
        binding.tv.text = user.toString()
    }
    fun logout(flag:Boolean) {
        token = null
        requireContext().getSharedPreferences("login_info", Context.MODE_PRIVATE)
            .edit().remove("token").commit()
    }

}
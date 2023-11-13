package org.jash.sports.ui

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.sports.R
import org.jash.sports.databinding.ActivityNewsBinding
import org.jash.sports.viewmodel.NewsViewModel

@Route(path = "/news/home")
class NewsActivity : BaseActivity<ActivityNewsBinding, NewsViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ARouter.getInstance().build("/news/news").navigation() as Fragment)
            .commit()
        binding.navigation.setOnItemSelectedListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, ARouter.getInstance().build(when(it.itemId) {
                    R.id.menu_video -> "/news/video"
                    R.id.menu_community -> "/news/community"
                    R.id.menu_mine -> "/news/mine"
                    else -> "/news/news"
                }).navigation() as Fragment)
                .commit()
            true
        }
        binding.fab.setOnClickListener {
            if (binding.fabLeft.isVisible) {
                binding.fab.animate().rotationBy(-45f).start()
                binding.fabLeft.animate().translationYBy(200f).alpha(0f)
                    .setListener(object : AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {

                        }

                        override fun onAnimationEnd(animation: Animator) {
                            binding.fabLeft.visibility = View.GONE
                        }

                        override fun onAnimationCancel(animation: Animator) {

                        }

                        override fun onAnimationRepeat(animation: Animator) {

                        }

                    })
                    .start()

                binding.fabRigth.animate().translationYBy(200f).alpha(0f)
                    .setListener(object : AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {

                        }

                        override fun onAnimationEnd(animation: Animator) {
                            binding.fabRigth.visibility = View.GONE
                        }

                        override fun onAnimationCancel(animation: Animator) {

                        }

                        override fun onAnimationRepeat(animation: Animator) {

                        }

                    })
                    .start()
            } else {
                binding.fab.animate().rotationBy(45f).start()

                binding.fabLeft.visibility = View.VISIBLE
                binding.fabRigth.visibility = View.VISIBLE
                binding.fabLeft.alpha = 0f
                binding.fabRigth.alpha = 0f
                binding.fabLeft.animate().translationYBy(-200f).alpha(1f)
                    .setListener(null)
                    .start()
                binding.fabRigth.animate().translationYBy(-200f).alpha(1f)
                    .setListener(null)
                    .start()
            }
        }
    }
}
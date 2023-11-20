package org.jash.common.utils

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import androidx.core.view.isVisible

class MyBehavior(context: Context, attributeSet: AttributeSet) : Behavior<View>(context, attributeSet) {
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {

        if (dyConsumed < -10) {
            child.animate()

                .alpha(1f)
                .start()
        }
        if (dyConsumed > 10) {
            child.animate()
                .alpha(0f)
                .start()
        }
    }
}
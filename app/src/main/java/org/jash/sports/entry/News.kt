package org.jash.sports.entry

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.drawee.view.SimpleDraweeView
import com.google.gson.annotations.SerializedName
import org.jash.sports.BR
import org.jash.sports.R
import java.util.Date

@Entity
data class News (
    val content: String,
    val createTime: Date,
    val flag: Int,
    @PrimaryKey
    val id: Int,
    val imgurl: String,
    val looks: Int,
    val ntid: Int,
    val suid: Int,
    val title: String,
    val comments:Int,
    val collected:Int
) :BaseObservable() {
    @Bindable
    var collects:Int = 0
        set(value)  {
            field = value
            notifyPropertyChanged(BR.collects)
        }
    fun showDetail() {
        ARouter.getInstance()
            .build("/news/detail")
            .withInt("id", id)
            .navigation()
    }
}
package org.jash.sports.entry

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import org.jash.sports.BR

class Sign:BaseObservable() {
    var phone:String = ""
    @Bindable
    var code:String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.code)
        }
}
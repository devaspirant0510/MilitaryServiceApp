package dev.seh.militaryserviceapp.util

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.data.type.MilitaryType
import timber.log.Timber

object GlobalBindAdapter {
    @JvmStatic
    @BindingAdapter(value=["buttonType","userSelectedType"], requireAll = true)
    fun setEnableSelectedButton(view:Button,buttonType:MilitaryType?,userSelectedType:MilitaryType?){
        Timber.e("button type :$buttonType miltype :$userSelectedType")
        if(buttonType==null || userSelectedType==null){
            view.setBackgroundColor(view.context.resources.getColor(R.color.default_button_color))
        }
        else if(buttonType==userSelectedType){
            view.setBackgroundColor(view.context.resources.getColor(R.color.mil_green))
        }else{
            view.setBackgroundColor(view.context.resources.getColor(R.color.default_button_color))
        }
    }

}
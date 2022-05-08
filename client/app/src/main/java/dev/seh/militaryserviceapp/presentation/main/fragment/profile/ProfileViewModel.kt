package dev.seh.militaryserviceapp.presentation.main.fragment.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor():BaseViewModel() {
    private val _isOnClickSetDate = MutableLiveData<Boolean>()
    val isOnClickSetDate:LiveData<Boolean> get()=_isOnClickSetDate
    fun initSettingEnlistmentDate(){
        _isOnClickSetDate.value = false
    }

    fun onClickSettingEnlistmentDate(){
        _isOnClickSetDate.value = true
    }

}
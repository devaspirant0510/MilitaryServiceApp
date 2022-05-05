package dev.seh.militaryserviceapp.base;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author SeungHo Lee
 * summary :
 */
abstract class BaseViewModel:ViewModel() {
    private val _errorState = MutableLiveData<Pair<Boolean, String>>(Pair(false,""))
    val errorState: LiveData<Pair<Boolean, String>>
        get() = _errorState

    open fun setError(message:String?){
        message?.let{
            _errorState.value = Pair(true,message)
        }
    }
}
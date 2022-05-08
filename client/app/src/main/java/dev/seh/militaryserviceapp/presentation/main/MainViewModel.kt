package dev.seh.militaryserviceapp.presentation.main;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import dev.seh.militaryserviceapp.data.entitiy.UserEntity
import dev.seh.militaryserviceapp.domain.usecase.AuthUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val authUseCase: AuthUseCase) :BaseViewModel(){
    private val _userInfo = MutableLiveData<UserEntity>()
    val userInfo:LiveData<UserEntity> get()=_userInfo

    private val _isAuthUser = MutableLiveData<Boolean>()
    val isAuthUser:LiveData<Boolean> get()=_isAuthUser

    fun checkAuth(){
        viewModelScope.launch {
            try{
                _userInfo.value = authUseCase.isAuthUser()
                _isAuthUser.value = true
            }catch (e:Exception){
                Timber.e("$e")
                _isAuthUser.value = false
                setError(e.message)
            }
        }
    }

    init {
    }

}
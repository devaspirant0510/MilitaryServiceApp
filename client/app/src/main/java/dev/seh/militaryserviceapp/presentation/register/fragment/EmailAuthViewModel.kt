package dev.seh.militaryserviceapp.presentation.register.fragment;

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import dev.seh.militaryserviceapp.data.vo.UserAuthInfo
import dev.seh.militaryserviceapp.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
@HiltViewModel
class EmailAuthViewModel @Inject constructor(private val authUserCase: AuthUseCase) :
    BaseViewModel() {
    val textInputEmail = MutableLiveData<String>()

    val textInputPassword = MutableLiveData<String>()

    private val _isUserAuth = MutableLiveData<Boolean>(false)
    val isUserAuth: LiveData<Boolean> get() = _isUserAuth

    private val _userAuth = MutableLiveData<AuthResult>()
    val userAuth:LiveData<AuthResult> get() = _userAuth

    private val _isNextPage = MutableLiveData<Boolean>(false)
    val isNextPage:LiveData<Boolean> get() = _isNextPage


    val isAuthFormValid = MediatorLiveData<Boolean>()

    fun onClickAuthButton() {
        Timber.e("onclick auth button")
        viewModelScope.launch {
            try {
                isAuthFormValid.value?.let {
                    val auth = authUserCase.signUp(
                        it,
                        textInputEmail.value?.trim()!!,
                        textInputPassword.value?.trim()!!
                    )
                    Timber.e("auth ${auth?.user?.uid}")
                    auth?.let { value->
                        Timber.e("auth auth")
                        _isUserAuth.value = true
                        Timber.e("value ${auth.user?.uid}")
                        _userAuth.value = value
                        Timber.e("${userAuth.value}")
                    }
                }
            } catch (e: Exception) {
                Timber.e("$e")
                setError(e.message)
            }
        }
    }
    fun onClickNextButton(){
        _isNextPage.value = true
    }
    fun getUserAuthInfo():UserAuthInfo?{
        try {
            Timber.e("userAuth ${userAuth.value}")
            val auth = authUserCase.getUserAuthInfo(userAuth.value!!, textInputEmail.value!!)
            Timber.e("auth auth $auth")
            return auth
        } catch (e: Exception) {
            Timber.e(e.message)
            setError(e.message)
            return null
        }
    }

    init {
        isAuthFormValid.addSource(textInputEmail) {
            isAuthFormValid.value = isValidLoginForm()
        }
        isAuthFormValid.addSource(textInputPassword) {
            isAuthFormValid.value = isValidLoginForm()
        }
    }
    companion object{
        @JvmStatic
        @BindingAdapter("setAuthEnabled")
        fun setAuthEnabled(view:View,value:AuthResult?){
            view.isEnabled = value == null

        }

        @JvmStatic
        @BindingAdapter("setAuthButtonText")
        fun setAuthButtonText(view: Button, value:AuthResult?){
            if(value!=null){
                view.text = "인증하기"
            }else{
                view.text = "인증완료"
            }
        }
    }

    private fun isValidLoginForm(): Boolean {
        return textInputEmail.value != "" && textInputPassword.value != ""
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("Email auth viewModel cleared")
    }
}
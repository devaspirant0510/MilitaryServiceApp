package dev.seh.militaryserviceapp.presentation.login;

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import dev.seh.militaryserviceapp.data.dto.LoginRequestDTO
import dev.seh.militaryserviceapp.domain.repository.Repository
import dev.seh.militaryserviceapp.domain.usecase.AuthUseCase
import dev.seh.militaryserviceapp.util.Constant
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) : BaseViewModel() {

    private val _introState = MutableLiveData<Int>(Constant.INTRO_DEFAULT)
    val introState: LiveData<Int> get() = _introState

    val isLoginFormValid = MediatorLiveData<Boolean>()

    val textInputEmail = MutableLiveData<String>("")
    val textInputPassword = MutableLiveData<String>("")

    private val _goToRegister = MutableLiveData<Boolean>(false)
    val goToRegister:LiveData<Boolean> get()=_goToRegister

    private val _isLoginSuccess = MutableLiveData<Boolean>(false)
    val isLoginSuccess:LiveData<Boolean> get()=_isLoginSuccess

    private val _isLoginState = MutableLiveData<Boolean>(false)
    val isLoginState: LiveData<Boolean> get() = _isLoginState

    private val _isRegisterState = MutableLiveData<Boolean>(false)
    val isRegisterState: LiveData<Boolean> get() = _isRegisterState

    private fun isValidLoginForm(): Boolean {
        return textInputEmail.value != "" && textInputPassword.value != ""
    }

    private fun settingValidFormLiveData() {
        isLoginFormValid.addSource(textInputPassword) {
            isLoginFormValid.value = isValidLoginForm()
        }
        isLoginFormValid.addSource(textInputEmail) {
            isLoginFormValid.value = isValidLoginForm()
        }
    }

    init {
        Timber.e("뷰모델 세팅")
        settingValidFormLiveData()
    }

    fun goToRegisterActivity(){
        _goToRegister.value = true
    }
    fun initGoToRegister(){
        _goToRegister.value = false
    }

    fun onClickLogin() {
        Timber.e("로그인")
        viewModelScope.launch {
            try {
                isLoginFormValid.value?.let {
                    Timber.e("로그인 요청 폼 올바름")
                    val user =
                        authUseCase.login(it, textInputEmail.value.toString().trim(), textInputPassword.value.toString())
                    _isLoginSuccess.value = true
                    Timber.e("${user.user?.uid}")
                }
            } catch (e: Exception) {
                Timber.e("에러 $e")
                setError(e.message)

            }
        }
    }

}
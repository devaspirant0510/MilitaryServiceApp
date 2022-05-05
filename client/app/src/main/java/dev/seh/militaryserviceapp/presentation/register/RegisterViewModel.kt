package dev.seh.militaryserviceapp.presentation.register;

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import dev.seh.militaryserviceapp.data.dto.CommonUserRegisterDTO
import dev.seh.militaryserviceapp.data.entitiy.UserEntity
import dev.seh.militaryserviceapp.data.type.MilitaryClass
import dev.seh.militaryserviceapp.data.type.MilitaryType
import dev.seh.militaryserviceapp.data.vo.CommonUserInfo
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
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase) :
    BaseViewModel() {
    private val _viewPagerPage = MutableLiveData<Int>(0)
    val viewPagerPage: LiveData<Int> get() = _viewPagerPage

    private val _stateUid = MutableLiveData<String>()
    val stateUid: LiveData<String> get() = _stateUid

    private val _stateEmail = MutableLiveData<String>()
    val stateEmail: LiveData<String> get() = _stateEmail

    private val _stateName = MutableLiveData<String>()
    val stateName: LiveData<String> get() = _stateName

    private val _stateIsSoldier = MutableLiveData<Boolean>()
    val stateIsSoldier: LiveData<Boolean> get() = _stateIsSoldier

    private val _stateSoldierType = MutableLiveData<MilitaryType?>()
    val stateSoldierType: LiveData<MilitaryType?> get() = _stateSoldierType

    private val _stateSoldierClass = MutableLiveData<MilitaryClass?>()
    val stateSoldierClass: LiveData<MilitaryClass?> get() = _stateSoldierClass

    private val _stateProfileURL = MutableLiveData<String?>()
    val stateProfileURL: LiveData<String?> get() = _stateProfileURL

    private val _stateEnlistmentDate = MutableLiveData<String?>()
    val stateEnlistmentDate: LiveData<String?> get() = _stateEnlistmentDate

    val isValidateRegisterUserInfo = MediatorLiveData<Boolean>()

    private fun checkValidateUserInfo(): Boolean {
        Timber.e("register validate ${stateEmail.value} ${stateUid.value} ${stateName.value} ${stateIsSoldier.value}")
        if (stateEmail.value != null && stateUid.value != null && stateName.value != null && stateIsSoldier.value != null) {
            return true
        }
        return false
    }

    fun actionNextPage() {
        _viewPagerPage.value = viewPagerPage.value?.plus(1)
    }

    fun setUserAuthSetting(authInfo: UserAuthInfo) {
        _stateUid.value = authInfo.uid
        _stateEmail.value = authInfo.email
    }

    fun setCommonUserSetting(commonUserInfo: CommonUserInfo) {
        _stateName.value = commonUserInfo.name
        _stateIsSoldier.value = commonUserInfo.isSoldier
    }

    fun registerCommonUser() {
        viewModelScope.launch {
            Timber.e("register")
            val data = CommonUserRegisterDTO(
                email = stateEmail.value.toString(),
                name = stateName.value.toString(),
                uid = stateUid.value.toString(),
                isSolider = stateIsSoldier.value!!
            )
            authUseCase.registerCommonUser(data)
        }
    }

    fun setMilitaryUserSetting(
        militaryType: MilitaryType,
        militaryClass: MilitaryClass,
        enlistmentDate: String
    ) {
        _stateSoldierType.value = militaryType
        _stateSoldierClass.value = militaryClass
        _stateEnlistmentDate.value = enlistmentDate
    }

    init {
        isValidateRegisterUserInfo.addSource(stateEmail) {
            isValidateRegisterUserInfo.value = checkValidateUserInfo()
        }
        isValidateRegisterUserInfo.addSource(stateUid) {
            isValidateRegisterUserInfo.value = checkValidateUserInfo()
        }
        isValidateRegisterUserInfo.addSource(stateName) {
            isValidateRegisterUserInfo.value = checkValidateUserInfo()
        }
        isValidateRegisterUserInfo.addSource(stateIsSoldier) {
            isValidateRegisterUserInfo.value = checkValidateUserInfo()
        }
    }
}
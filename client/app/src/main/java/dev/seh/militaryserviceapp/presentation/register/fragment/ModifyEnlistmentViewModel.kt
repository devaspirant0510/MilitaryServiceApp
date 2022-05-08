package dev.seh.militaryserviceapp.presentation.register.fragment;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import dev.seh.militaryserviceapp.data.type.MilitaryType
import dev.seh.militaryserviceapp.domain.usecase.AuthUseCase
import dev.seh.militaryserviceapp.util.MilitaryUtils
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
@HiltViewModel
class ModifyEnlistmentViewModel @Inject constructor(private val authUseCase: AuthUseCase) : BaseViewModel() {
    private val _selectedMilitaryType = MutableLiveData<MilitaryType>()
    val selectedMilitaryType: LiveData<MilitaryType> get() = _selectedMilitaryType

    private val _enlistmentDate = MutableLiveData<String>()
    val enlistmentDate:LiveData<String> get() =_enlistmentDate

    private val _dischargeDate = MutableLiveData<String>()
    val dischargeDate:LiveData<String> get()=_dischargeDate


    private val _isOnClickSetDate = MutableLiveData<Boolean>(false)
    val isOnClickSetDate:LiveData<Boolean> get() =_isOnClickSetDate

    fun onClickArmy() {
        _selectedMilitaryType.value = MilitaryType.ARMY
    }

    fun onClickNavy() {
        _selectedMilitaryType.value = MilitaryType.NAVY
    }

    fun onClickAirForce() {
        _selectedMilitaryType.value = MilitaryType.AIR_FORCE
    }

    fun onClickMarine() {
        _selectedMilitaryType.value = MilitaryType.MARINE
    }

    fun onClickSetDate(){
        if(selectedMilitaryType.value==null){
            setError("군종을 선택해주세요")
        }else{
            _isOnClickSetDate.value = true
        }
    }
    fun onClickSetDateInit(){
        _isOnClickSetDate.value = false
    }
    fun setEnlistmentDate(date:String){
        _enlistmentDate.value = date
    }
    fun modifyUserInfo(){
        viewModelScope.launch {
            authUseCase.getUserId()?.let{userId->
                authUseCase.modifyUserInfo(userId,selectedMilitaryType.value!!,enlistmentDate.value!!)
            }
        }
    }

}
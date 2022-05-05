package dev.seh.militaryserviceapp.presentation.register.fragment;

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import dev.seh.militaryserviceapp.data.vo.CommonUserInfo
import dev.seh.militaryserviceapp.domain.usecase.AuthUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
@HiltViewModel
class RegisterFormViewModel @Inject constructor(private val authUseCase: AuthUseCase) :BaseViewModel(){
    val isSoldierTrue = MutableLiveData<Boolean>()
    val isSoldierFalse = MutableLiveData<Boolean>()
    val inputUserName = MutableLiveData<String>()

    val isFormValid = MediatorLiveData<Boolean>()

    private val _onClickRegister =  MutableLiveData<Boolean>(false)
    val onClickRegister:LiveData<Boolean> get()=_onClickRegister

    private fun checkFormValidate():Boolean{
        inputUserName.value?.let{
            Timber.e("true: ${isSoldierTrue.value} | false: ${isSoldierFalse.value} ")
            if(isSoldierFalse.value==true || isSoldierTrue.value == true) {
                return true
            }
            return false
        }
        return false
    }
    fun onClickNextStepButton(){

    }
    fun onClickRegisterButton(){
        _onClickRegister.value = true
    }
    fun getCommonUserInfo():CommonUserInfo?{
        isFormValid.value?.let{
            if(it){
                return CommonUserInfo(
                    inputUserName.value.toString().trim(),
                    isSoldierTrue.value!!
                )
            }
            return null

        }
        return null
    }
    init {
        isFormValid.addSource(isSoldierFalse){
            isFormValid.value = checkFormValidate()
        }
        isFormValid.addSource(isSoldierTrue){
            isFormValid.value = checkFormValidate()
        }
        isFormValid.addSource(inputUserName){
            isFormValid.value = checkFormValidate()
        }
    }
    companion object{
        @JvmStatic
        @BindingAdapter(value=["nextIsFormValid","nextIsSolider"], requireAll = true)
        fun setBindNextStepButton(view: Button, isFormValid:Boolean, isSolider:Boolean){
            if(isFormValid && !isSolider){
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.GONE
            }
        }

        @JvmStatic
        @BindingAdapter(value=["signUpIsFormValid","signUpIsSolider"], requireAll = true)
        fun setBindSignUpButton(view: Button, isFormValid:Boolean, isSolider:Boolean){
            if(isFormValid && !isSolider){
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.GONE
            }
        }
    }
}
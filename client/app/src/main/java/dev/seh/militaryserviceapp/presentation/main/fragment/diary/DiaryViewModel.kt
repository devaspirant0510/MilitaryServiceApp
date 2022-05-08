package dev.seh.militaryserviceapp.presentation.main.fragment.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.seh.militaryserviceapp.base.BaseViewModel
import dev.seh.militaryserviceapp.domain.usecase.DiaryUseCase
import dev.seh.militaryserviceapp.util.DateUtils
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(private val diaryUseCase: DiaryUseCase) :BaseViewModel(){
    private val _currentDate = MutableLiveData<String>()
    val currentDate:LiveData<String> get()=_currentDate

    fun onClickNextDay(){
        currentDate.value?.let{ date->
            _currentDate.value = diaryUseCase.setNextDate(date)
        }

    }
    fun onClickPreviousDay(){
        currentDate.value?.let{ date->
            _currentDate.value = diaryUseCase.setPreviousDate(date)
        }
    }

    init {
        _currentDate.value = DateUtils.getTodayDate()
    }
}
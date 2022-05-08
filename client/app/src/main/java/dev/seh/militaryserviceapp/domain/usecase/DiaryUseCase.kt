package dev.seh.militaryserviceapp.domain.usecase;

import dev.seh.militaryserviceapp.domain.repository.Repository
import dev.seh.militaryserviceapp.util.DateUtils
import java.util.*
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
class DiaryUseCase @Inject constructor(private val repository: Repository){
    fun setNextDate(date:String):String{
        DateUtils.convertStringToDate(date)?.let{ calendar->
            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1)
            return DateUtils.convertDateString(calendar)
        }
        return date
    }
    fun setPreviousDate(date:String):String{
        DateUtils.convertStringToDate(date)?.let{ calendar->
            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-1)
            return DateUtils.convertDateString(calendar)
        }
        return date
    }

}
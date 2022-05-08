package dev.seh.militaryserviceapp.util;

import dev.seh.militaryserviceapp.data.type.MilitaryType
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author SeungHo Lee
 * summary :
 */
object DateUtils {
    private val calendarInstance by lazy {
        Calendar.getInstance()
    }
    private val getSimpleFormatter by lazy {
        SimpleDateFormat("yyyy년 MM월 dd일",Locale.KOREA)
    }

    fun initCalendar() {
        calendarInstance.time = Date()
    }

    fun getYear(): Int = calendarInstance.get(Calendar.YEAR)

    fun getMonth(): Int = calendarInstance.get(Calendar.MONTH)

    fun getDays(): Int = calendarInstance.get(Calendar.DAY_OF_MONTH)

    fun getTodayDate():String = convertDateString(getYear(), getMonth(), getDays())

    fun convertDateString(calendar: Calendar):String{
        return getSimpleFormatter.format(calendar.time)
    }
    fun convertDateString(year: Int, month: Int, day: Int): String {
        val tempCalendar = Calendar.getInstance()
        tempCalendar.set(year,month,day)
        return getSimpleFormatter.format(tempCalendar.time)
    }
    fun convertStringToDate(date:String):Calendar?{
        getSimpleFormatter.parse(date)?.let{
            val returnCalendar = Calendar.getInstance()
            returnCalendar.time = it
            return returnCalendar
        }
        return null
    }

    fun convertCalendarToString(calendar: Calendar): String = convertDateString(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )


    fun operationDischargeDate(militaryType: MilitaryType, year: Int, month: Int, day: Int):String {
        calendarInstance.set(Calendar.YEAR, year)
        calendarInstance.set(Calendar.MONTH, month)
        calendarInstance.set(
            Calendar.DAY_OF_MONTH,
            day + MilitaryUtils.getPeriodOfService(militaryType)
        )
        return convertCalendarToString(calendarInstance)
    }
    fun operationDischargeCounter():String{
        val tempCal = Calendar.getInstance()
        tempCal.set(2023,10,5)
        val getDischargeDay = (tempCal.timeInMillis- calendarInstance.timeInMillis)/(24 * 60 * 60 * 1000)
        Timber.e("${getDischargeDay.toInt()}")
        return "전역일 D-${getDischargeDay.toInt()}"
    }
}
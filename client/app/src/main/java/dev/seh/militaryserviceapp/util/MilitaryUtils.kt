package dev.seh.militaryserviceapp.util;

import dev.seh.militaryserviceapp.data.type.MilitaryType

/**
 * @author SeungHo Lee
 * summary :
 */
object MilitaryUtils {
    const val PERIOD_OF_ARMY_SERVICE = 545
    const val PERIOD_OF_MARINE_SERVICE = 545
    const val PERIOD_OF_NAVY_SERVICE = 606
    const val PERIOD_OF_AIR_FORCE_SERVICE = 637

    fun getPeriodOfService(militaryType: MilitaryType): Int {
        return when (militaryType) {
            MilitaryType.ARMY -> PERIOD_OF_ARMY_SERVICE
            MilitaryType.NAVY -> PERIOD_OF_NAVY_SERVICE
            MilitaryType.AIR_FORCE -> PERIOD_OF_AIR_FORCE_SERVICE
            MilitaryType.MARINE -> PERIOD_OF_MARINE_SERVICE
        }
    }
    fun getMilitaryName(militaryType: MilitaryType):String{
        return when(militaryType){
            MilitaryType.ARMY -> "ARMY"
            MilitaryType.NAVY -> "NAVY"
            MilitaryType.AIR_FORCE -> "AIR_FORCE"
            MilitaryType.MARINE -> "MARINE"
        }
    }
}
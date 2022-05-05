package dev.seh.militaryserviceapp.data.entitiy;

import dev.seh.militaryserviceapp.data.type.MilitaryClass
import dev.seh.militaryserviceapp.data.type.MilitaryType

/**
 * @author SeungHo Lee
 * summary :
 */
data class UserEntity(
    val uid: String,
    val name: String,
    val email: String,
    val isSolider:Boolean,
    val militaryType:MilitaryType?,
    val militaryClass :MilitaryClass?,
    val profileURL: String?,
    val enlistmentDate: String?,
) {
}
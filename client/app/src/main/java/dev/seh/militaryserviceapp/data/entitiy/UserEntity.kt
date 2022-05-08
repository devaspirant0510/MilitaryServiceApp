package dev.seh.militaryserviceapp.data.entitiy;

import com.google.firebase.database.IgnoreExtraProperties
import dev.seh.militaryserviceapp.data.type.MilitaryClass
import dev.seh.militaryserviceapp.data.type.MilitaryType

/**
 * @author SeungHo Lee
 * summary :
 */
@IgnoreExtraProperties
data class UserEntity(
    val uid: String,
    val name: String,
    val email: String,
    val solider: Boolean,
    val militaryType: MilitaryType?,
    val militaryClass: MilitaryClass?,
    val profileURL: String?,
    val enlistmentDate: String?,
) {
    constructor() : this("", "", "", false, null, null, null, null)
}
package dev.seh.militaryserviceapp.util

import android.util.Patterns

object AuthUtils {
    fun checkValidEmail(email:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
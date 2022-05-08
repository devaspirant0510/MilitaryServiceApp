package dev.seh.militaryserviceapp.domain.repository;

import android.content.SharedPreferences
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dev.seh.militaryserviceapp.data.dto.CommonUserRegisterDTO
import dev.seh.militaryserviceapp.data.entitiy.UserEntity
import dev.seh.militaryserviceapp.data.mapper.Mapper
import dev.seh.militaryserviceapp.data.type.MilitaryType
import dev.seh.militaryserviceapp.util.FireBaseConstant
import dev.seh.militaryserviceapp.util.SharedConstants
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * @author SeungHo Lee
 * summary :
 */
class Repository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val firebaseAuth: FirebaseAuth,
    private val database: DatabaseReference
) {
    suspend fun authEmailSignIn(email: String, password: String): AuthResult {
        return firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
    }

    suspend fun authEmailLogin(email: String, password: String): AuthResult {
        return firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
    }

    suspend fun registerCommonUser(commonUserDTO: CommonUserRegisterDTO) {
        database.child(FireBaseConstant.PATH_USER).child(commonUserDTO.uid).setValue(commonUserDTO).await()
    }

    suspend fun setUserInfo(userId:String,militaryType: String,enlistmentDate:String){
        database.child(FireBaseConstant.PATH_USER).child(userId).child("militaryType").setValue(militaryType).await()
        database.child(FireBaseConstant.PATH_USER).child(userId).child("enlistmentDate").setValue(enlistmentDate).await()
        database.child(FireBaseConstant.PATH_USER).child(userId).child("solider").setValue(true).await()
    }

    suspend fun showAllUser(): List<UserEntity> {
        val data = database.child(FireBaseConstant.PATH_USER).get().await()
        return Mapper.snapShotChildrenToIterable<UserEntity>(data)

    }

    fun getSharedValue(value: String): String? {
        return sharedPreferences.getString(value, "")
    }
    fun setSharedValue(key:String,value:String){
        sharedPreferences.edit().putString(key,value).apply()
    }
    // SharedPreference 에 저장된 모든 데이터 삭제
    fun cleanAllSharedData() {
        sharedPreferences.edit().clear().apply()
    }

}
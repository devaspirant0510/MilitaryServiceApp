package dev.seh.militaryserviceapp.domain.repository;

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.seh.militaryserviceapp.data.dto.CommonUserRegisterDTO
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
class Repository @Inject constructor()  {
    private val firebaseAuth = Firebase.auth
    private var database: DatabaseReference = Firebase.database.reference
    suspend fun authEmailSignIn(email: String, password: String): AuthResult {
        return firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
    }
    suspend fun authEmailLogin(email:String,password: String):AuthResult{
        return firebaseAuth
            .signInWithEmailAndPassword(email,password)
            .await()
    }
    suspend fun registerCommonUser(commonUserDTO:CommonUserRegisterDTO){
        database.child("user").push().setValue(commonUserDTO).await()
    }
}
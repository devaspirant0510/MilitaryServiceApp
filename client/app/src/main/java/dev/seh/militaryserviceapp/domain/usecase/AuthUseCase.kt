package dev.seh.militaryserviceapp.domain.usecase;

import android.util.Patterns
import com.google.firebase.auth.AuthResult
import dev.seh.militaryserviceapp.data.dto.CommonUserRegisterDTO
import dev.seh.militaryserviceapp.data.vo.UserAuthInfo
import dev.seh.militaryserviceapp.domain.repository.Repository
import dev.seh.militaryserviceapp.util.AuthUtils
import timber.log.Timber
import javax.inject.Inject

/**
 * @author SeungHo Lee
 * summary :
 */
class AuthUseCase @Inject constructor(private val repository: Repository) {
    suspend fun signUp(isValidForm: Boolean, email: String, password: String): AuthResult? {
        // 입력폼이 유효할때만 로직 실행
        Timber.e("signup")
        if (isValidForm) {
            Timber.e("form")
            // 이메일이 유효한지 체크
            if (!AuthUtils.checkValidEmail(email)) {
                throw Exception("email is not valid")
            }
            return repository.authEmailSignIn(email, password)
        } else {
            Timber.e("not  form")
            throw Exception("form is not valid")
        }
    }

    suspend fun login(isValidForm: Boolean, email: String, password: String): AuthResult {
        Timber.e("로그인 유즈케이스")
        if (isValidForm) {

            if (!AuthUtils.checkValidEmail(email)) {
                throw Exception("email is not valid")
            }
            return repository.authEmailLogin(email, password)
        } else {
            throw Exception("form is not valid")
        }
    }

    fun getUserAuthInfo(auth:AuthResult,email:String):UserAuthInfo{
        Timber.e("auth3434 ${auth.user?.uid}")
        Timber.e("aua ${auth.user?.email}")
        Timber.e("aua ${auth.user?.displayName}")
        auth.user?.let{ firebaseUser ->
            Timber.e("firebase ${firebaseUser.uid}")
            return UserAuthInfo(
                uid = firebaseUser.uid,
                email = email
            )
        }
        throw Exception("인증정보가 잘못되었습니다. 다시 시도해주세요")
    }

    suspend fun registerCommonUser(commonUser: CommonUserRegisterDTO):Boolean{
        try {
            repository.registerCommonUser(commonUser)
            return true
        }catch (e:Exception){
            throw Exception(e.message)
        }
    }
}
package dev.seh.militaryserviceapp.domain.usecase;

import android.util.Patterns
import com.google.firebase.auth.AuthResult
import dev.seh.militaryserviceapp.data.dto.CommonUserRegisterDTO
import dev.seh.militaryserviceapp.data.entitiy.UserEntity
import dev.seh.militaryserviceapp.data.type.MilitaryType
import dev.seh.militaryserviceapp.data.vo.UserAuthInfo
import dev.seh.militaryserviceapp.domain.repository.Repository
import dev.seh.militaryserviceapp.util.AuthUtils
import dev.seh.militaryserviceapp.util.MilitaryUtils
import dev.seh.militaryserviceapp.util.SharedConstants
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
    fun getUserId():String?{
        return repository.getSharedValue(SharedConstants.USER_ID)
    }

    suspend fun login(isValidForm: Boolean, email: String, password: String): AuthResult {
        Timber.e("로그인 유즈케이스")
        if (isValidForm) {

            if (!AuthUtils.checkValidEmail(email)) {
                throw Exception("email is not valid")
            }
            val userLogin = repository.authEmailLogin(email, password)
            userLogin.user?.uid?.let{ userUid->
                repository.setSharedValue(SharedConstants.USER_ID,userUid)
                return userLogin
            }
            throw Exception("로그인 실패")
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
    suspend fun isAuthUser():UserEntity{
        val userId = repository.getSharedValue(SharedConstants.USER_ID)
        Timber.e("userid :$userId")
        userId?.let{
            val userList = repository.showAllUser()
            Timber.e("userlist $userList")
            userList.forEach { item->
                if(item.uid==userId){
                    Timber.e("인증된 유저 $item")
                    return  item
                }
            }
            throw Exception("해당 유저는 가입된 유저가 아닙니다. 회원가입해주세요")
        }
        throw Exception("로그인해주세요")
    }
    suspend fun modifyUserInfo(userId:String,militaryType: MilitaryType,date:String){
        repository.setSharedValue(SharedConstants.USER_ENLISTMENT_DATE,date)
        repository.setSharedValue(SharedConstants.USER_MILITARY_TYPE,MilitaryUtils.getMilitaryName(militaryType))
        repository.setUserInfo(userId,MilitaryUtils.getMilitaryName(militaryType),date)
    }
}
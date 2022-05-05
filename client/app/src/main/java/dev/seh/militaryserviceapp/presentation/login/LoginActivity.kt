package dev.seh.militaryserviceapp.presentation.login;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseActivity
import dev.seh.militaryserviceapp.databinding.ActivityLoginBinding
import dev.seh.militaryserviceapp.presentation.main.MainActivity
import dev.seh.militaryserviceapp.presentation.register.RegisterActivity
import dev.seh.militaryserviceapp.util.Constant
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        mBinding.viewModel = viewModel
        setErrorObserver()
        setObserver()

    }

    override fun setObserver() {
        viewModel.isLoginFormValid.observe(this){

        }
        viewModel.goToRegister.observe(this){
            if(it){
                val intent = Intent(applicationContext,RegisterActivity::class.java)
                startActivity(intent)
                viewModel.initGoToRegister()
            }
        }
        viewModel.isLoginSuccess.observe(this){
            if(it){
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
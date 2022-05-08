package dev.seh.militaryserviceapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseActivity
import dev.seh.militaryserviceapp.databinding.ActivityMainBinding
import dev.seh.militaryserviceapp.presentation.login.LoginActivity
import dev.seh.militaryserviceapp.presentation.main.fragment.community.CommunityFragment
import dev.seh.militaryserviceapp.presentation.main.fragment.diary.DiaryFragment
import dev.seh.militaryserviceapp.presentation.main.fragment.home.HomeFragment
import dev.seh.militaryserviceapp.presentation.main.fragment.profile.ProfileFragment
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        settingBottomNaviFragment()
        setErrorObserver()
        setObserver()

    }

    override fun onStart() {
        super.onStart()
        viewModel.checkAuth()
    }

    private fun settingBottomNaviFragment(){
        supportFragmentManager.beginTransaction().add(mBinding.frameLayout.id,HomeFragment()).commit()
        mBinding.bottomNavigationView.setOnItemSelectedListener { it->
            when(it.itemId){
                R.id.bn_home->(replaceFragment(HomeFragment()))
                R.id.bn_community->(replaceFragment(CommunityFragment()))
                R.id.bn_diary->replaceFragment(DiaryFragment())
                R.id.bn_profile->replaceFragment(ProfileFragment())
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(mBinding.frameLayout.id,fragment).commit()
    }

    override fun setObserver() {
        viewModel.isAuthUser.observe(this){
            if(!it){
                val intent = Intent(applicationContext,LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
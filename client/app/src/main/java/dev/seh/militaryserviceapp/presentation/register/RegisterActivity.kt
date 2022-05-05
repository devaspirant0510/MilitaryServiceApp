package dev.seh.militaryserviceapp.presentation.register;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseActivity
import dev.seh.militaryserviceapp.databinding.ActivityRegisterBinding
import dev.seh.militaryserviceapp.presentation.register.adapter.RegisterPagerAdapter
import dev.seh.militaryserviceapp.presentation.register.fragment.*
import dev.seh.militaryserviceapp.util.ViewPagerAnimation
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterViewModel>(R.layout.activity_register) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        setErrorObserver()
        setObserver()
        setViewPagerAdapter()
    }

    private fun setViewPagerAdapter() {
        val viewPagerAdapter = RegisterPagerAdapter(this@RegisterActivity)
        viewPagerAdapter.addFragmentAll(
            EmailAuthFragment(),
            RegisterFormFragment(),
            MilitaryInfoFormFragment(),
            RegisterSuccessFragment()
        )
        mBinding.vpRegisterPager.apply{
            adapter = viewPagerAdapter
            isUserInputEnabled = false
            setPageTransformer(ViewPagerAnimation.ZoomOutPageTransformer())
        }
        TabLayoutMediator(mBinding.tabLayout, mBinding.vpRegisterPager) { _, _ -> }.attach()
    }

    override fun setObserver() {
        viewModel.stateEmail.observe(this){
            Timber.e("email   : $it")
        }
        viewModel.stateUid.observe(this){
            Timber.e("uid  : $it")
        }
        viewModel.isValidateRegisterUserInfo.observe(this){
            Timber.e("register validate form $it")
        }


        viewModel.viewPagerPage.observe(this){
            it?.let{ value->
                mBinding.vpRegisterPager.setCurrentItem(value,true)
            }
        }

    }
}
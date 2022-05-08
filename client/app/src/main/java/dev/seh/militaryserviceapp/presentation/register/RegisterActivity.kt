package dev.seh.militaryserviceapp.presentation.register;

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseActivity
import dev.seh.militaryserviceapp.databinding.ActivityRegisterBinding
import dev.seh.militaryserviceapp.presentation.register.adapter.RegisterPagerAdapter
import dev.seh.militaryserviceapp.presentation.register.fragment.*
import dev.seh.militaryserviceapp.util.Constant
import dev.seh.militaryserviceapp.util.ViewPagerAnimation
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterViewModel>(R.layout.activity_register) {
    private lateinit var viewpagerAdapter: RegisterPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        setErrorObserver()
        setObserver()
        initViewPager()
        Timber.e("#${intent.getStringExtra(Constant.INTENT_REGISTER_ACTIVITY)}")
        if(intent.getStringExtra(Constant.INTENT_REGISTER_ACTIVITY)==Constant.MODIFY_ENLISTMENT_DATE){
            setModifyEnlistmentDate()
        }else{
            setRegisterViewPager()
        }
    }
    private fun initViewPager(){
        viewpagerAdapter = RegisterPagerAdapter(this@RegisterActivity)
        mBinding.vpRegisterPager.apply{
            adapter = viewpagerAdapter
            isUserInputEnabled = false
            setPageTransformer(ViewPagerAnimation.ZoomOutPageTransformer())
        }
        TabLayoutMediator(mBinding.tabLayout, mBinding.vpRegisterPager) { _, _ -> }.attach()
    }
    private fun setModifyEnlistmentDate(){
        viewpagerAdapter.addFragmentAll(
            ModifyEnlistmentFragment(),
            SuccessMessageFragment(),
        )
    }

    private fun setRegisterViewPager() {
        viewpagerAdapter.addFragmentAll(
            EmailAuthFragment(),
            RegisterFormFragment(),
            MilitaryInfoFormFragment(),
            SuccessMessageFragment()
        )
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
package dev.seh.militaryserviceapp.presentation.register.fragment;

import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentRegisterSuccessBinding

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class SuccessMessageFragment :BaseFragment<FragmentRegisterSuccessBinding,SuccessMessageViewModel>(
    R.layout.fragment_register_success){
    override fun setObserver() {
    }
}
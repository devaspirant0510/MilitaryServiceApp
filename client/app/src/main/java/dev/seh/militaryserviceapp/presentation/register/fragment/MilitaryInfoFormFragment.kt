package dev.seh.militaryserviceapp.presentation.register.fragment;

import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentMilitaryInfoFormBinding

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class MilitaryInfoFormFragment :BaseFragment<FragmentMilitaryInfoFormBinding,MilitaryInfoFormViewModel>(
    R.layout.fragment_military_info_form){
    override fun setObserver() {
    }
}
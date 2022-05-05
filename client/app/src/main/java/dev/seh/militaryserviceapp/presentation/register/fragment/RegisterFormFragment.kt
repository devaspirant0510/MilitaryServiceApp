package dev.seh.militaryserviceapp.presentation.register.fragment;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentRegisterFormBinding
import dev.seh.militaryserviceapp.presentation.register.RegisterViewModel
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class RegisterFormFragment :BaseFragment<FragmentRegisterFormBinding,RegisterFormViewModel>(R.layout.fragment_register_form){
    private lateinit var rootViewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[RegisterFormViewModel::class.java]
        rootViewModel = ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.viewModel = viewModel
        setErrorObserver(view.context)
        setObserver()

    }
    override fun setObserver() {
        viewModel.isSoldierTrue.observe(viewLifecycleOwner){
            Timber.e("is true $it")
        }
        viewModel.isSoldierFalse.observe(viewLifecycleOwner){
            Timber.e("is false $it")
        }
        viewModel.isFormValid.observe(viewLifecycleOwner){
            Timber.e("form $it")
        }
        viewModel.onClickRegister.observe(viewLifecycleOwner){
            viewModel.getCommonUserInfo()?.let{
                Timber.e(it.toString())
                Timber.e("register  : ${rootViewModel.stateUid.value} ${rootViewModel.stateEmail.value} ${rootViewModel.stateIsSoldier.value} ${rootViewModel.stateName.value}")
                rootViewModel.setCommonUserSetting(it)
                rootViewModel.registerCommonUser()
            }

        }
    }

}
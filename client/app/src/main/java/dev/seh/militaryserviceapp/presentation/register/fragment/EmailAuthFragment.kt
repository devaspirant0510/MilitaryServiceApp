package dev.seh.militaryserviceapp.presentation.register.fragment;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentEmailAuthBinding
import dev.seh.militaryserviceapp.presentation.register.RegisterViewModel
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class EmailAuthFragment :
    BaseFragment<FragmentEmailAuthBinding, EmailAuthViewModel>(R.layout.fragment_email_auth) {
    private lateinit var rootViewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[EmailAuthViewModel::class.java]
        rootViewModel = ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.viewModel = viewModel
        setObserver()
        setErrorObserver(view.context)
    }

    override fun setObserver() {
        viewModel.isAuthFormValid.observe(viewLifecycleOwner) {}
        // 이메일 인증 성공시
        viewModel.isUserAuth.observe(viewLifecycleOwner) { isSuccess->
        }
        viewModel.userAuth.observe(viewLifecycleOwner){
            if(it!=null){
                val authInfo = viewModel.getUserAuthInfo()
                Timber.e("authoinofff ${viewModel.userAuth.value?.user?.uid}")
                Timber.e("authoinof $authInfo")
                authInfo?.let { auth->
                    rootViewModel.setUserAuthSetting(auth)
                }
                Timber.e("user uid ${it.user?.uid}")
            }
        }
        viewModel.isNextPage.observe(viewLifecycleOwner){
            if(it){
                rootViewModel.actionNextPage()
            }
        }

    }

    override fun onPause() {
        super.onPause()
        Timber.e("pause")
    }

    override fun onStop() {
        super.onStop()
        Timber.e("stop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.e("destroy")
    }
}
package dev.seh.militaryserviceapp.presentation.main.fragment.profile

import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentProfileBinding
import dev.seh.militaryserviceapp.presentation.main.MainViewModel
import dev.seh.militaryserviceapp.presentation.register.RegisterActivity
import dev.seh.militaryserviceapp.util.Constant

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {
    private lateinit var rootViewModel:MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        rootViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.rootViewModel = rootViewModel
        mBinding.viewModel = viewModel
        setObserver()
        settingCardView()
    }

    private fun settingCardView() {
        mBinding.componentProfileCard.apply {
            icProfileImg.background = ShapeDrawable(OvalShape())
            icProfileImg.clipToOutline = true

        }
    }

    override fun setObserver() {
        viewModel.isOnClickSetDate.observe(viewLifecycleOwner){
            if(it){
                val intent = Intent(activity,RegisterActivity::class.java)
                intent.putExtra(Constant.INTENT_REGISTER_ACTIVITY,Constant.MODIFY_ENLISTMENT_DATE)
                startActivity(intent)
            }
        }
    }
}
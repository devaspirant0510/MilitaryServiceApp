package dev.seh.militaryserviceapp.presentation.main.fragment.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentDiaryBinding

@AndroidEntryPoint
class DiaryFragment:BaseFragment<FragmentDiaryBinding,DiaryViewModel>(R.layout.fragment_diary) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[DiaryViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = viewModel
        setObserver()
        setErrorObserver(view.context)
    }
    override fun setObserver() {
    }

}
package dev.seh.militaryserviceapp.presentation.main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding,HomeViewModel>(R.layout.fragment_home){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun setObserver() {
    }

}
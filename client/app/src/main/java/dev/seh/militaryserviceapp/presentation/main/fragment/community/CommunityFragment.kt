package dev.seh.militaryserviceapp.presentation.main.fragment.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.databinding.FragmentCommunityBinding

class CommunityFragment :BaseFragment<FragmentCommunityBinding,CommunityViewModel>(R.layout.fragment_community){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(getViewModelStoreOwner())[CommunityViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObserver()
        setErrorObserver(view.context)
    }
    override fun setObserver() {
    }

}
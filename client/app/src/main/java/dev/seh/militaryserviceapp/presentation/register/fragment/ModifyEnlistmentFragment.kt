package dev.seh.militaryserviceapp.presentation.register.fragment;

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.seh.militaryserviceapp.R
import dev.seh.militaryserviceapp.base.BaseFragment
import dev.seh.militaryserviceapp.data.type.MilitaryType
import dev.seh.militaryserviceapp.databinding.FragmentModifyEnlistmentBinding
import dev.seh.militaryserviceapp.util.DateUtils
import dev.seh.militaryserviceapp.util.MilitaryUtils
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
@AndroidEntryPoint
class ModifyEnlistmentFragment:BaseFragment<FragmentModifyEnlistmentBinding,ModifyEnlistmentViewModel>(
    R.layout.fragment_modify_enlistment) {
    private lateinit var dateListener:DatePickerDialog.OnDateSetListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[ModifyEnlistmentViewModel::class.java]
        dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            Timber.e("year:$year month:${month+1} dat:$day")
            viewModel.setEnlistmentDate(DateUtils.convertDateString(year,month,day))
            mBinding.btnDischargeDate.text =DateUtils.operationDischargeDate(MilitaryType.ARMY,year,month,day)
            mBinding.tvEnlistmentDate.text = DateUtils.convertDateString(year,month,day)
            DateUtils.initCalendar()

        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = viewModel
        setObserver()
        setErrorObserver(view.context)
        setContextObserver(view.context)
    }
    private fun setContextObserver(ctx:Context){
        viewModel.isOnClickSetDate.observe(viewLifecycleOwner){
            if(it){
                DatePickerDialog(ctx,dateListener,DateUtils.getYear(),DateUtils.getMonth(),DateUtils.getDays()).show()
                viewModel.onClickSetDateInit()
            }
        }

    }
    override fun setObserver() {
        viewModel.selectedMilitaryType.observe(viewLifecycleOwner){
            Timber.e("miltype observer $it")
        }
    }
}
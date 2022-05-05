package dev.seh.militaryserviceapp.base;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @author SeungHo Lee
 * summary :
 */
abstract class BaseFragment<T:ViewDataBinding,V:BaseViewModel>(@LayoutRes private val layoutRes:Int):Fragment() {
    lateinit var viewModel:V
    private var bindingObj:T? = null
    protected val mBinding:T by lazy{
        bindingObj!!
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingObj = DataBindingUtil.inflate(layoutInflater,layoutRes,container,false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    protected fun setErrorObserver(ctx:Context){
        viewModel.errorState.observe(viewLifecycleOwner){
            if(it.first){
                Toast.makeText(ctx,it.second, Toast.LENGTH_SHORT).show()
            }
        }
    }
    protected fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: Exception) {
        this
    }
    abstract fun setObserver()

    override fun onDestroyView() {
        super.onDestroyView()
        bindingObj = null
    }
}
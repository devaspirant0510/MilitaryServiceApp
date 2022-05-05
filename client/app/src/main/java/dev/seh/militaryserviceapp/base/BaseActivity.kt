package dev.seh.militaryserviceapp.base;

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author SeungHo Lee
 * summary :
 */
abstract class BaseActivity<T:ViewDataBinding,V:BaseViewModel>(@LayoutRes private val layout:Int) :AppCompatActivity(){
    lateinit var mBinding:T
    lateinit var viewModel:V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,layout)
    }
    protected fun setErrorObserver(){
        viewModel.errorState.observe(this){
            if(it.first){
                Toast.makeText(applicationContext,it.second, Toast.LENGTH_SHORT).show()
            }
        }
    }
    abstract fun setObserver()
}
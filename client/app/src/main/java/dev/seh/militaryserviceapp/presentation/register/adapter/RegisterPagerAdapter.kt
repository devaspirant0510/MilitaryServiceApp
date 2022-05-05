package dev.seh.militaryserviceapp.presentation.register.adapter;

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author SeungHo Lee
 * summary :
 */
class RegisterPagerAdapter(fa:FragmentActivity):FragmentStateAdapter(fa){
    private val mPagers = mutableListOf<Fragment>()

    override fun getItemCount(): Int = mPagers.size


    override fun createFragment(position: Int): Fragment {
        return mPagers[position]
    }

    fun addFragmentAll(vararg fragments:Fragment){
        mPagers.addAll(fragments)
        notifyDataSetChanged()
    }
}
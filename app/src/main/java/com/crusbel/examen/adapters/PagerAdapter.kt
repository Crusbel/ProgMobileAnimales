package com.crusbel.examen.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm:FragmentManager)
    :FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val listFragments = ArrayList<Fragment>()

    override fun getItem(position: Int) = listFragments[position]
    override fun getCount() = listFragments.size
    fun addFrag(f:Fragment) = listFragments.add(f)
}
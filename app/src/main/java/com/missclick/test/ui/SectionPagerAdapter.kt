package com.missclick.test.ui

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.missclick.test.ui.gallery.ForViewPagerFragment


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return if(position == 0){
            Log.e("adapter", "schedule")
            ForViewPagerFragment()
        } else{
            Log.e("adapter", "schedule")
            ForViewPagerFragment()
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}
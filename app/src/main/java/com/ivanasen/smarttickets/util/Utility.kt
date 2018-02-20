package com.ivanasen.smarttickets.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class Utility {
    companion object {
        fun loadFragment(@IdRes containerViewId: Int, fragmentManager: FragmentManager, fragment: Fragment) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(containerViewId, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
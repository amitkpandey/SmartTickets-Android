package com.ivanasen.smarttickets.util

import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat.startActivity
import com.ivanasen.smarttickets.ui.MainActivity
import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.widget.Toast
import com.ivanasen.smarttickets.R


class Utility {
    companion object {
        val MIN_PASSWORD_LENGTH = 8
        val ONE_ETHER_IN_WEI = Math.pow(10.0, 18.0).toLong()
        const val INFURA_ETHER_PRICE_IN_USD_URL = "https://api.infura.io/v1/ticker/ethusd"

        fun loadFragment(@IdRes containerViewId: Int, fragmentManager: FragmentManager, fragment: Fragment) {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(containerViewId, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        fun isValidPassword(password: String): Boolean {
            return password.length >= MIN_PASSWORD_LENGTH
        }

        fun launchActivity(context: Context, cls: Class<*>) {
            val intent = Intent(context, cls)
            context.startActivity(intent)
        }

        fun copyToClipboard(context: Context, label: String,  text: String) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label, text)
            clipboard.primaryClip = clip

            Toast.makeText(context,
                    context.getString(R.string.copied_to_clipboard),
                    Toast.LENGTH_SHORT)
                    .show()
        }
    }

}
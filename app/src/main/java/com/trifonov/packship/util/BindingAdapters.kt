package com.trifonov.packship.util

import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.trifonov.packship.R

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}


@BindingAdapter("app:onToolbarMenuItemClick")
fun setOnToolbarMenuItemClick(toolbar: androidx.appcompat.widget.Toolbar, function: Runnable) {

    toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener,
        androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            function.run()
            return true
        }
    })
}
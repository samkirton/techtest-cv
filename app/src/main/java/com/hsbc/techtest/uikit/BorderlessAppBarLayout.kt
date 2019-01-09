package com.hsbc.techtest.uikit

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import com.google.android.material.appbar.AppBarLayout

class BorderlessAppBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppBarLayout(context, attrs) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val stateListAnimator = StateListAnimator()
            stateListAnimator.addState(intArrayOf(0), ObjectAnimator.ofFloat(this, "elevation", 0.1f))
            setStateListAnimator(stateListAnimator)
        }
    }
}
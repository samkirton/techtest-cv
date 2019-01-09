package com.hsbc.techtest.app.cv.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import com.hsbc.techtest.R
import com.hsbc.techtest.api.LinkJson

class LinkButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.LinkButton
) : AppCompatButton(context, attrs, defStyleAttr) {

    lateinit var link: LinkJson

    fun populate(link: LinkJson) {
        this.link = link
        text = link.name
    }

    companion object {
        fun create(linksJson: LinkJson, context: Context): LinkButton {
            return with(
                LayoutInflater.from(context).inflate(
                    R.layout.links_viewgroup_item,
                    null,
                    false) as LinkButton) {
                populate(linksJson)
                this
            }
        }
    }
}
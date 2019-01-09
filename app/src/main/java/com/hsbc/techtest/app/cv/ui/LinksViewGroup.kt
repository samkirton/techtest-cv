package com.hsbc.techtest.app.cv.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.hsbc.techtest.R
import com.hsbc.techtest.api.LinkJson
import com.hsbc.techtest.uikit.Interaction
import io.reactivex.subjects.PublishSubject

class LinksViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = LinearLayout.HORIZONTAL
    }

    fun populate(links: List<LinkJson>, interaction: PublishSubject<Interaction<LinkJson>>) {
        removeAllViews()
        links.forEach { linksJson ->
            addView(with(LinkButton.create(linksJson, context)) {
                layoutParams = with(LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                )) {
                    marginEnd = context.resources.getDimensionPixelOffset(R.dimen.app_padding_small)
                    this
                }
                setOnClickListener {
                    interaction.onNext(Interaction(-1, linksJson))
                }
                this
            })
        }
    }
}
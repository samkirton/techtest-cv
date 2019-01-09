package com.hsbc.techtest.app.cv.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hsbc.techtest.api.ExperienceJson
import com.hsbc.techtest.api.LinkJson
import com.hsbc.techtest.uikit.Interaction
import com.hsbc.techtest.uikit.gone
import com.hsbc.techtest.uikit.visible
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.experience_item_view.view.*

class ExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populate(value: ExperienceJson, interaction: PublishSubject<Interaction<LinkJson>>) {
        itemView.experience_item_view_title_label.text = value.title
        itemView.experience_item_view_date_range_label.text = value.dateRange
        itemView.experience_item_view_description_label.text = value.description

        if (value.links.isNotEmpty()) {
            itemView.experience_item_view_linksViewGroup.visible()
            itemView.experience_item_view_linksViewGroup.populate(value.links, interaction)
        } else {
            itemView.experience_item_view_linksViewGroup.gone()
        }
    }
}
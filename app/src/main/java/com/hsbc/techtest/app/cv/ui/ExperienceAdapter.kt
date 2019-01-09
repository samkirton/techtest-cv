package com.hsbc.techtest.app.cv.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsbc.techtest.R
import com.hsbc.techtest.api.ExperienceJson
import com.hsbc.techtest.api.LinkJson
import com.hsbc.techtest.uikit.Interaction
import io.reactivex.subjects.PublishSubject

class ExperienceAdapter(
    context: Context,
    val interaction: PublishSubject<Interaction<LinkJson>>,
    private val inflater: LayoutInflater = LayoutInflater.from(context),
    private val data: MutableList<ExperienceJson> = ArrayList()
) : RecyclerView.Adapter<ExperienceViewHolder>() {

    fun populate(experienceJson: List<ExperienceJson>) {
        data.clear()
        data.addAll(experienceJson)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        return ExperienceViewHolder(inflater.inflate(R.layout.experience_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        holder.populate(data[position], interaction)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
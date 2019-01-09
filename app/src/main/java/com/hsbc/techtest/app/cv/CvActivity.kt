package com.hsbc.techtest.app.cv

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import com.hsbc.techtest.R
import com.hsbc.techtest.api.CvJson
import com.hsbc.techtest.api.LinkJson
import com.hsbc.techtest.app.MviActivity
import com.hsbc.techtest.app.ViewModelFactory
import com.hsbc.techtest.app.cv.ui.ExperienceAdapter
import com.hsbc.techtest.uikit.Interaction
import com.hsbc.techtest.uikit.gone
import com.hsbc.techtest.uikit.visible
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.cv_activity.*
import javax.inject.Inject

class CvActivity
    : MviActivity<CvIntent, CvRenderAction, CvViewState, CvViewLayout>(), CvViewLayout {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var render: CvViewRenderer

    private lateinit var experienceAdapter: ExperienceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cv_activity)

        val adapterInteraction: PublishSubject<Interaction<LinkJson>> = PublishSubject.create()
        experienceAdapter = ExperienceAdapter(this, adapterInteraction)
        cv_activity_experience_recyclerView.adapter = experienceAdapter

        cv_activity_appbarlayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appbar, _ ->
            if (cv_activity_appbarlayout.isEnabled) {
                val offsetAlpha = appbar.y / appbar.totalScrollRange
                cv_activity_header_container.alpha = 1 - offsetAlpha * -1
            }
        })
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }

    override fun intents(): Observable<CvIntent> = Observable.merge(
        Observable.just(CvIntent.Init),
        cv_activity_errorRetryView.retryClick().map {
            CvIntent.Retry
        },
        experienceAdapter.interaction.map { linkJson ->
            CvIntent.LinkSelected(linkJson.data)
        }
    )

    override fun layout(): CvViewLayout = this

    override fun model(): CvViewModel = getViewModel(viewModelFactory)

    override fun render(): CvViewRenderer = render

    override fun showProgress() {
        cv_activity_progressBar.visible()
        cv_activity_errorRetryView.gone()
        cv_activity_appbarlayout.gone()
    }

    override fun showError() {
        cv_activity_progressBar.gone()
        cv_activity_errorRetryView.visible()
        cv_activity_errorRetryView.populate(getString(R.string.cv_error_body))
    }

    override fun showCv(cv: CvJson) {
        cv_activity_progressBar.gone()
        cv_activity_appbarlayout.visible()
        cv_activity_name.text = cv.name
        cv_activity_job_title_value.text = cv.title
        cv_activity_industry_exp_value.text = getString(R.string.cv_years, cv.stats.yearsInIndustry)
        cv_activity_users_reached_value.text = cv.stats.appReach

        experienceAdapter.populate(cv.experience)
    }

    override fun navigateToUrl(url: String) {
        model().publish(CvIntent.Idle)
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
package com.hsbc.techtest.app.cv

import com.memtrip.mxandroid.MxViewRenderer
import javax.inject.Inject

class CvViewRenderer @Inject internal constructor() : MxViewRenderer<CvViewLayout, CvViewState> {
    override fun layout(layout: CvViewLayout, state: CvViewState): Unit = when (state.view) {
        CvViewState.View.Idle -> {
        }
        CvViewState.View.OnProgress -> {
            layout.showProgress()
        }
        CvViewState.View.OnError -> {
            layout.showError()
        }
        is CvViewState.View.ShowCv -> {
            layout.showCv(state.view.cv)
        }
        is CvViewState.View.LinkSelected -> {
            layout.navigateToUrl(state.view.linkJson.url)
        }
    }
}
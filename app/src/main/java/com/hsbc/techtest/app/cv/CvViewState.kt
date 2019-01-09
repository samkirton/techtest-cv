package com.hsbc.techtest.app.cv

import com.hsbc.techtest.api.CvJson
import com.hsbc.techtest.api.LinkJson
import com.memtrip.mxandroid.MxViewState

data class CvViewState(
    val view: View
) : MxViewState {

    sealed class View {
        object Idle : View()
        object OnProgress : View()
        object OnError : View()
        data class ShowCv(val cv: CvJson) : View()
        data class LinkSelected(val linkJson: LinkJson) : View()
    }
}
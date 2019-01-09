package com.hsbc.techtest.app.cv

import com.hsbc.techtest.api.CvJson
import com.hsbc.techtest.api.LinkJson
import com.memtrip.mxandroid.MxRenderAction

sealed class CvRenderAction : MxRenderAction {
    object Idle : CvRenderAction()
    object OnProgress : CvRenderAction()
    object OnError : CvRenderAction()
    data class ShowCv(val cv: CvJson) : CvRenderAction()
    data class LinkSelected(val linkJson: LinkJson) : CvRenderAction()
}
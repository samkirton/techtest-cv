package com.hsbc.techtest.app.cv

import com.hsbc.techtest.api.LinkJson
import com.memtrip.mxandroid.MxViewIntent

sealed class CvIntent : MxViewIntent {
    object Idle : CvIntent()
    object Init : CvIntent()
    object Retry : CvIntent()
    data class LinkSelected(val linkJson: LinkJson) : CvIntent()
}
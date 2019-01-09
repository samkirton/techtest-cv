package com.hsbc.techtest.app.cv

import com.hsbc.techtest.api.CvJson
import com.memtrip.mxandroid.MxViewLayout

interface CvViewLayout : MxViewLayout {
    fun showProgress()
    fun showError()
    fun showCv(cv: CvJson)
    fun navigateToUrl(url: String)
}
package com.hsbc.techtest.app.cv

import android.app.Application
import com.hsbc.techtest.api.Api
import com.hsbc.techtest.util.RxScheduler
import com.memtrip.mxandroid.MxViewModel
import io.reactivex.Observable
import javax.inject.Inject

class CvViewModel @Inject internal constructor(
    private val api: Api,
    private val rx: RxScheduler,
    application: Application
) : MxViewModel<CvIntent, CvRenderAction, CvViewState>(
    CvViewState(view = CvViewState.View.Idle),
    application
) {

    override fun dispatcher(intent: CvIntent): Observable<CvRenderAction> = when (intent) {
        CvIntent.Idle -> Observable.just(CvRenderAction.Idle)
        is CvIntent.Init -> getCv()
        CvIntent.Retry -> getCv()
        is CvIntent.LinkSelected -> Observable.just(CvRenderAction.LinkSelected(intent.linkJson))
    }

    override fun reducer(previousState: CvViewState, renderAction: CvRenderAction): CvViewState = when (renderAction) {
        CvRenderAction.Idle -> previousState.copy(view = CvViewState.View.Idle)
        CvRenderAction.OnProgress -> previousState.copy(view = CvViewState.View.OnProgress)
        CvRenderAction.OnError -> previousState.copy(view = CvViewState.View.OnError)
        is CvRenderAction.ShowCv -> previousState.copy(view = CvViewState.View.ShowCv(renderAction.cv))
        is CvRenderAction.LinkSelected -> previousState.copy(view = CvViewState.View.LinkSelected(renderAction.linkJson))
    }

    private fun getCv(): Observable<CvRenderAction> {
        return api.getCv().observeOn(rx.main()).subscribeOn(rx.thread()).map { response ->
            if (response.isSuccessful) {
                CvRenderAction.ShowCv(response.body()!!)
            } else {
                CvRenderAction.OnError
            }
        }.onErrorReturn {
            CvRenderAction.OnError
        }.toObservable().startWith(CvRenderAction.OnProgress)
    }
}
package daniel.avila.ricknmortykmm.shared.base

import daniel.avila.ricknmortykmm.shared.domain.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<View : IBaseView>(
    private val executor: Executor
) : IBasePresenter<View>, CoroutineScope {
    // TODO: best option
    //    private var weakViewReference: WeakReference<View>? = null

    private var view: View? = null
    private val isViewAttached: Boolean get() = view != null

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + executor.main

    override fun attach(view: View) {
        if (!isViewAttached) {
            this.view = view
        }
    }

    override fun detach() {
        job.cancel()
        view = null
    }
}
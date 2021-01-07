package daniel.avila.ricknmortykmm.shared.base

import daniel.avila.ricknmortykmm.shared.domain.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<View : IBaseView>(
    private val executor: Executor
) : IBasePresenter<View>, CoroutineScope {
    // TODO: best option
    //    private var weakViewReference: WeakReference<View>? = null

    private var viewAttach: View? = null
    private val isViewAttached: Boolean get() = viewAttach != null

    private var job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + executor.main

    protected val view: View
        get() = viewAttach ?: throw IllegalStateException("View don't attached")

    override fun attach(view: View) {
        job = SupervisorJob()
        if (!isViewAttached) {
            this.viewAttach = view
        }
    }

    override fun detach() {
        job.cancel()
        viewAttach = null
    }
}
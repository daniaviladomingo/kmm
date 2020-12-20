package daniel.avila.ricknmortykmm.shared.base

import daniel.avila.ricknmortykmm.shared.domain.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BasePresenter<out View : IViewResourceState>(
    private val view: View,
    private val executor: Executor
): CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + executor.main

    fun detach() = job.cancel()
}
package daniel.avila.ricknmortykmm.shared.base

import daniel.avila.ricknmortykmm.shared.domain.IExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class BasePresenter<out View : IViewResourceState>(
    private val view: View,
    private val executor: IExecutor
): CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + executor.main()

    fun detach() = job.cancel()
}
package daniel.avila.ricknmortykmm.shared.base.executor

import daniel.avila.ricknmortykmm.shared.domain.MainDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

abstract class MainExecutor : IExecutorScope, CoroutineScope, KoinComponent {

    private val mainDispatcher: MainDispatcher by inject()

    private var job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + mainDispatcher.dispatcher

    override fun attach() {
        job = SupervisorJob()
    }

    override fun detach() {
        job.cancel()
    }

    protected fun <T> launch(flow: Flow<T>, onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            flow.catch {
                onError(it)
            }.collect {
                onSuccess(it)
            }
        }
    }
}
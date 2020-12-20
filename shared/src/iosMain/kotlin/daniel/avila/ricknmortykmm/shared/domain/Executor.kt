package daniel.avila.ricknmortykmm.shared.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.CoroutineContext

actual class Executor{
    actual val main: CoroutineDispatcher = MainLoopDispatcher
}

object MainLoopDispatcher: CoroutineDispatcher(){
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock { block.run() }
    }
}
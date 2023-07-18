package daniel.avila.ricknmortykmm.shared.base.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.CoroutineContext

actual class MainDispatcher {
    actual val dispatcher: CoroutineDispatcher = Dispatchers.Main
}

package daniel.avila.ricknmortykmm.shared.domain

import kotlinx.coroutines.CoroutineDispatcher

expect class Executor() {
    val main: CoroutineDispatcher
}
package daniel.avila.ricknmortykmm.shared.domain

import kotlinx.coroutines.CoroutineDispatcher

expect class MainDispatcher() {
    val dispatcher: CoroutineDispatcher
}
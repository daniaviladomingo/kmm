package daniel.avila.ricknmortykmm.shared.domain

import kotlinx.coroutines.CoroutineDispatcher

interface IExecutor {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}
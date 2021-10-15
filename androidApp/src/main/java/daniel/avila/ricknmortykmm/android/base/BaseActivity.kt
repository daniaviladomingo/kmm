package daniel.avila.ricknmortykmm.android.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import daniel.avila.ricknmortykmm.shared.base.IBaseView
import daniel.avila.ricknmortykmm.shared.base.executor.IExecutorScope
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.MainDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity: AppCompatActivity(), CoroutineScope, IBaseView, KoinComponent {
    private lateinit var job: Job
    private val mainDispatcher: MainDispatcher by inject()

    override val coroutineContext: CoroutineContext
        get() = mainDispatcher.dispatcher + job

    abstract fun executor(): IExecutorScope

    override fun onStart() {
        job = SupervisorJob()
        executor().attach()
        super.onStart()
    }

    override fun onStop() {
        job.cancel()
        executor().detach()
        super.onStop()
    }

    override fun <T> managementResourceState(
        resource: BasicUiState<T>,
        success: (data: T) -> Unit
    ) {
        when (resource) {
            is BasicUiState.Loading -> {
//                activityViewBase.view.visibility = View.VISIBLE
//                activityViewBase.viewError.visibility = View.GONE
//                activityViewBase.viewEmpty.visibility = View.GONE
//                activityViewBase.viewProgress.visibility = View.VISIBLE
            }
            is BasicUiState.Success<T> -> {
//                activityViewBase.view.visibility = View.VISIBLE
//                activityViewBase.viewError.visibility = View.GONE
//                activityViewBase.viewEmpty.visibility = View.GONE
//                activityViewBase.viewProgress.visibility = View.GONE
                success(resource.data)
            }
            is BasicUiState.Empty -> {
//                activityViewBase.view.visibility = View.GONE
//                activityViewBase.viewError.visibility = View.GONE
//                activityViewBase.viewEmpty.visibility = View.VISIBLE
//                activityViewBase.viewProgress.visibility = View.GONE
            }
            is BasicUiState.Error -> {
//                activityViewBase.view.visibility = View.GONE
//                activityViewBase.viewError.visibility = View.VISIBLE
//                activityViewBase.viewError.findViewById<TextView>(R.id.error_message).text =
//                    resource.message ?: resources.getString(R.string.label_error_result)
//                activityViewBase.viewEmpty.visibility = View.GONE
//                activityViewBase.viewProgress.visibility = View.GONE
            }
            is BasicUiState.None -> Unit
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
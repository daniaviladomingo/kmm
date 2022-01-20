package daniel.avila.ricknmortykmm.android.base

import androidx.activity.ComponentActivity
import daniel.avila.ricknmortykmm.shared.base.executor.IExecutorScope

abstract class BaseActivity : ComponentActivity() {
    protected abstract val vm: Array<IExecutorScope>

    override fun onStart() {
        vm.forEach { it.attach() }
        super.onStart()
    }

    override fun onStop() {
        vm.forEach { it.detach() }
        super.onStop()
    }
}
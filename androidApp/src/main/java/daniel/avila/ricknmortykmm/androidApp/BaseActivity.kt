package daniel.avila.ricknmortykmm.androidApp

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.base.IBaseView
import daniel.avila.ricknmortykmm.shared.base.Resource

abstract class BaseActivity: AppCompatActivity(), IBaseView {
    abstract fun getPresenter(): IBasePresenter<*>

    override fun onStart() {
        (getPresenter() as IBasePresenter<IBaseView>).attach(this)
        super.onStart()
    }

    override fun onStop() {
        getPresenter().detach()
        super.onStop()
    }

    override fun managementResourceState(resource: Resource<*>) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
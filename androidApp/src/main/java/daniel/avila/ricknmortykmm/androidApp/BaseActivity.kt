package daniel.avila.ricknmortykmm.androidApp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import daniel.avila.ricknmortykmm.shared.base.BasePresenter
import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.base.IBaseView
import daniel.avila.ricknmortykmm.shared.base.Resource

abstract class BaseActivity: AppCompatActivity(), IBaseView {
//    abstract val basePresenter: IBasePresenter<IBaseView>

    abstract fun getPresenter(): IBasePresenter<IBaseView>

    override fun managementResourceState(resource: Resource<*>) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter().attach(this)
    }

    override fun onDestroy() {
        getPresenter().detach()
        super.onDestroy()
    }
}
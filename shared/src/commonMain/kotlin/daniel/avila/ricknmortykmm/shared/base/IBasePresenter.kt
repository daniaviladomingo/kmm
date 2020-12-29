package daniel.avila.ricknmortykmm.shared.base

interface IBasePresenter<View> {
    fun attach(view: View)
    fun detach()
}
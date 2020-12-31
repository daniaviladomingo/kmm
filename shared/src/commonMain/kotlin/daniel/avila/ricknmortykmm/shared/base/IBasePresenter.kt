package daniel.avila.ricknmortykmm.shared.base

interface IBasePresenter<in View : IBaseView> {
    fun attach(view: View)
    fun detach()
}
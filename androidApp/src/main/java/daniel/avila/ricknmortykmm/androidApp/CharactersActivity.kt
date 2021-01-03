package daniel.avila.ricknmortykmm.androidApp

import android.os.Bundle
import android.util.Log
import daniel.avila.ricknmortykmm.androidApp.databinding.ActivityMainBinding
import daniel.avila.ricknmortykmm.shared.apiCharacterMapper
import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.dataRemote
import daniel.avila.ricknmortykmm.shared.data_cache.CacheDataImp
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.DatabaseDriverFactory
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharacterUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.executor
import daniel.avila.ricknmortykmm.shared.features.CharactersPresenter
import daniel.avila.ricknmortykmm.shared.features.ICharactersPresenter
import daniel.avila.ricknmortykmm.shared.features.ICharactersView
import daniel.avila.ricknmortykmm.shared.repository.RepositoryImp

class CharactersActivity : BaseActivity(), ICharactersView {
    val cacheData = CacheDataImp(DatabaseDriverFactory(this))

    val repository = RepositoryImp(
        cacheData = cacheData,
        remoteData = dataRemote,
        apiCharacterMapper = apiCharacterMapper
    )

    private val presenter: ICharactersPresenter<ICharactersView> = CharactersPresenter(GetCharacterUseCase(repository), executor)

    override fun getPresenter(): IBasePresenter<*> = presenter

    private val adapter: CharactersAdapter = CharactersAdapter {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.recycler.adapter = adapter
        setContentView(binding.root)

        presenter.loadCharacters()
    }

    override fun displayCharacters(characters: List<Character>) {
        Log.d("aaa", Thread.currentThread().name)
        adapter.submitList(characters)
        characters.forEach { Log.d("aaa", "$it") }
    }
}

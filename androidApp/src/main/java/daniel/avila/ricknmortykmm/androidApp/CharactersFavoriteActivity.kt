package daniel.avila.ricknmortykmm.androidApp

import android.content.Intent
import android.os.Bundle
import daniel.avila.ricknmortykmm.androidApp.databinding.ActivityMainBinding
import daniel.avila.ricknmortykmm.shared.apiCharacterMapper
import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.dataRemote
import daniel.avila.ricknmortykmm.shared.data_cache.CacheDataImp
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.DatabaseDriverFactory
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.executor
import daniel.avila.ricknmortykmm.shared.features.favorites.CharactersFavoritesPresenter
import daniel.avila.ricknmortykmm.shared.features.favorites.ICharactersFavoritePresenter
import daniel.avila.ricknmortykmm.shared.features.favorites.ICharactersFavoritesView
import daniel.avila.ricknmortykmm.shared.features.favorites.INavigatorCharactersFavorites
import daniel.avila.ricknmortykmm.shared.repository.RepositoryImp

class CharactersFavoriteActivity : BaseActivity(), ICharactersFavoritesView, INavigatorCharactersFavorites {
    val cacheData = CacheDataImp(DatabaseDriverFactory(this))

    val repository = RepositoryImp(
        cacheData = cacheData,
        remoteData = dataRemote,
        apiCharacterMapper = apiCharacterMapper
    )

    private val presenter: ICharactersFavoritePresenter<ICharactersFavoritesView> =
        CharactersFavoritesPresenter(
            GetCharactersFavoritesUseCase(repository), this, executor
        )

    override fun getPresenter(): IBasePresenter<*> = presenter

    private val adapter: CharactersAdapter = CharactersAdapter {
        presenter.onCharacterClick(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.recycler.adapter = adapter
        setContentView(binding.root)

        presenter.loadCharacters()
    }

    override fun displayCharacters(characters: List<Character>) {
        adapter.submitList(characters)
    }

    override fun navigateToDetail(character: Character) {
        startActivity(Intent(this, DetailCharacterActivity::class.java))
    }
}

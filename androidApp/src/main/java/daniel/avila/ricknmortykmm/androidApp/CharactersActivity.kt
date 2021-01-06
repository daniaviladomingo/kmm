package daniel.avila.ricknmortykmm.androidApp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import daniel.avila.ricknmortykmm.androidApp.DetailCharacterActivity.Companion.CHARACTER
import daniel.avila.ricknmortykmm.androidApp.databinding.ActivityMainBinding
import daniel.avila.ricknmortykmm.androidApp.model.mapper.CharacterMapper
import daniel.avila.ricknmortykmm.shared.apiCharacterMapper
import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.dataRemote
import daniel.avila.ricknmortykmm.shared.data_cache.CacheDataImp
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.DatabaseDriverFactory
import daniel.avila.ricknmortykmm.shared.domain.interactors.GetCharactersUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.executor
import daniel.avila.ricknmortykmm.shared.features.characters.CharactersPresenter
import daniel.avila.ricknmortykmm.shared.features.characters.ICharactersPresenter
import daniel.avila.ricknmortykmm.shared.features.characters.ICharactersView
import daniel.avila.ricknmortykmm.shared.features.characters.INavigatorCharacters
import daniel.avila.ricknmortykmm.shared.repository.RepositoryImp

class CharactersActivity : BaseActivity(), ICharactersView, INavigatorCharacters {
    val cacheData = CacheDataImp(DatabaseDriverFactory(this))

    val repository = RepositoryImp(
        cacheData = cacheData,
        remoteData = dataRemote,
        apiCharacterMapper = apiCharacterMapper
    )

    private val characterMapper = CharacterMapper()

    private val presenter: ICharactersPresenter<ICharactersView> =
        CharactersPresenter(GetCharactersUseCase(repository), this, executor)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_favorite -> {
                presenter.onFavoritesClick()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun displayCharacters(characters: List<Character>) {
        adapter.submitList(characters)
    }

    override fun navigateToDetail(character: Character) {
        startActivity(Intent(this, DetailCharacterActivity::class.java).apply {
            putExtra(CHARACTER, characterMapper.map(character))
        })
    }

    override fun navigateToFavorites() {
        startActivity(Intent(this, CharactersFavoriteActivity::class.java))
    }
}

package daniel.avila.ricknmortykmm.androidApp

import android.content.Intent
import android.os.Bundle
import daniel.avila.ricknmortykmm.androidApp.databinding.ActivityMainBinding
import daniel.avila.ricknmortykmm.androidApp.model.mapper.CharacterMapper
import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.features.favorites.ICharactersFavoritePresenter
import daniel.avila.ricknmortykmm.shared.features.favorites.ICharactersFavoritesView
import daniel.avila.ricknmortykmm.shared.features.favorites.INavigatorCharactersFavorites
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CharactersFavoriteActivity : BaseActivity(), ICharactersFavoritesView, INavigatorCharactersFavorites {
    private val presenter: ICharactersFavoritePresenter<ICharactersFavoritesView> by inject { parametersOf(this) }
    private val mapper: CharacterMapper by inject()

    override fun getPresenter(): IBasePresenter<*> = presenter

    private val adapter: CharactersAdapter = CharactersAdapter {
        presenter.onCharacterClick(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.recycler.adapter = adapter
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorites"
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCharacters()
    }

    override fun displayCharacters(characters: List<Character>) {
        adapter.submitList(characters)
    }

    override fun navigateToDetail(character: Character) {
        startActivity(Intent(this, DetailCharacterActivity::class.java).apply {
            putExtra(DetailCharacterActivity.CHARACTER, mapper.map(character))
        })
    }
}

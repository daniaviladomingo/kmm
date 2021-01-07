package daniel.avila.ricknmortykmm.androidApp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import daniel.avila.ricknmortykmm.androidApp.databinding.ActivityDetailCharacterBinding
import daniel.avila.ricknmortykmm.androidApp.model.CharacterParcelable
import daniel.avila.ricknmortykmm.androidApp.model.mapper.CharacterMapper
import daniel.avila.ricknmortykmm.shared.apiCharacterMapper
import daniel.avila.ricknmortykmm.shared.base.IBasePresenter
import daniel.avila.ricknmortykmm.shared.dataRemote
import daniel.avila.ricknmortykmm.shared.data_cache.CacheDataImp
import daniel.avila.ricknmortykmm.shared.data_cache.sqldelight.DatabaseDriverFactory
import daniel.avila.ricknmortykmm.shared.domain.interactors.AddCharacterToFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.IsCharacterFavoriteUseCase
import daniel.avila.ricknmortykmm.shared.domain.interactors.RemoveCharacterFromFavoritesUseCase
import daniel.avila.ricknmortykmm.shared.domain.model.Character

import daniel.avila.ricknmortykmm.shared.executor
import daniel.avila.ricknmortykmm.shared.features.detail.CharacterDetailPresenter
import daniel.avila.ricknmortykmm.shared.features.detail.ICharacterDetailPresenter
import daniel.avila.ricknmortykmm.shared.features.detail.ICharacterDetailView
import daniel.avila.ricknmortykmm.shared.repository.RepositoryImp

class DetailCharacterActivity : BaseActivity(), ICharacterDetailView {
    val cacheData = CacheDataImp(DatabaseDriverFactory(this))

    val repository = RepositoryImp(
        cacheData = cacheData,
        remoteData = dataRemote,
        apiCharacterMapper = apiCharacterMapper
    )

    private val characterMapper = CharacterMapper()

    private val presenter: ICharacterDetailPresenter<ICharacterDetailView> =
        CharacterDetailPresenter(
            IsCharacterFavoriteUseCase(repository),
            AddCharacterToFavoritesUseCase(repository),
            RemoveCharacterFromFavoritesUseCase(repository),
            executor
        )

    private var menuFavorite = false
    private lateinit var character: Character

    override fun getPresenter(): IBasePresenter<*> = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_character)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail"

        val binding = ActivityDetailCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.run {
            with(binding) {
                character =
                    characterMapper.inverseMap(getParcelable<CharacterParcelable>(CHARACTER)!!)
                presenter.isFavorite(character.id)
                name.text = character.name
                Glide.with(image).load(character.image).into(image)
                originSpecies.text =
                    "${character.origin}, ${character.species}"
                status.text = character.status.name
            }
        }
    }

    override fun isFavorite(isFavorite: Boolean) {
        menuFavorite = isFavorite
        invalidateOptionsMenu()
    }

    override fun addedFavorite() {
        Toast.makeText(this, "Aded to favorites", Toast.LENGTH_SHORT).show()
        menuFavorite = true
        invalidateOptionsMenu()
    }

    override fun removedFavorite() {
        Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
        menuFavorite = false
        invalidateOptionsMenu()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_favorite -> {
                presenter.addCharacterToFavorite(character)
                true
            }
            R.id.action_unfavorite -> {
                presenter.removeCharacterFromFavorite(character.id)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(
            if (menuFavorite) R.menu.menu_favorite else R.menu.menu_unfavorite,
            menu
        )
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        const val CHARACTER = "character"
    }
}
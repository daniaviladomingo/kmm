package daniel.avila.ricknmortykmm.android.features

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import daniel.avila.ricknmortykmm.android.R
import daniel.avila.ricknmortykmm.android.base.BaseActivity
import daniel.avila.ricknmortykmm.android.databinding.ActivityDetailCharacterBinding
import daniel.avila.ricknmortykmm.android.model.CharacterParcelable
import daniel.avila.ricknmortykmm.android.model.mapper.CharacterMapper
import daniel.avila.ricknmortykmm.shared.base.executor.IExecutorScope
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.Status.*
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CharacterDetailActivity : BaseActivity()/*, ICharacterDetailView */ {
    private val vm: CharacterDetailViewModel by inject()
    private val mapper: CharacterMapper by inject()

    private var menuFavorite = false
    private lateinit var character: Character

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
                    mapper.inverseMap(getParcelable<CharacterParcelable>(CHARACTER)!!)
                name.text = character.name
                Glide.with(image).load(character.image).into(image)
                originSpecies.text = String.format("%s, %s", character.origin, character.species)
                status.text = character.status.name
                status.setTextColor(
                    when (character.status) {
                        ALIVE -> Color.GREEN
                        DEAD -> Color.RED
                        UNKNOWN -> Color.YELLOW
                    }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()

        vm.setEvent(CharacterDetailContract.Event.CheckIfIsFavorite(character.id))

        launch {
            vm.uiState.collect {
                managementResourceState(it.isFavorite) { isFavorite ->
                    menuFavorite = isFavorite
                    invalidateOptionsMenu()
                }
            }
        }

        launch {
            vm.effect.collect {
                val msg = when (it) {
                    CharacterDetailContract.Effect.CharacterAdded -> {
                        menuFavorite = true
                        "Added to favorites"
                    }
                    CharacterDetailContract.Effect.CharacterRemoved -> {
                        menuFavorite = false
                        "Removed from favorites"
                    }
                }

                Toast.makeText(this@CharacterDetailActivity, msg, Toast.LENGTH_SHORT).show()
                invalidateOptionsMenu()
            }
        }
    }

//    override fun isFavorite(isFavorite: Boolean) {
//        menuFavorite = isFavorite
//        invalidateOptionsMenu()
//    }

//    override fun addedFavorite() {
//        Toast.makeText(this, "Aded to favorites", Toast.LENGTH_SHORT).show()
//        menuFavorite = true
//        invalidateOptionsMenu()
//    }

//    override fun removedFavorite() {
//        Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
//        menuFavorite = false
//        invalidateOptionsMenu()
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_favorite -> {
                vm.setEvent(CharacterDetailContract.Event.OnAddCharacterToFavorite(character))
                true
            }
            R.id.action_unfavorite -> {
                vm.setEvent(CharacterDetailContract.Event.RemoveCharacterToFavorite(character.id))
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

    override fun executor(): IExecutorScope = vm

    companion object {
        const val CHARACTER = "character"
    }
}
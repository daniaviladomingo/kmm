package daniel.avila.ricknmortykmm.android.features

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import daniel.avila.ricknmortykmm.android.CharactersAdapter
import daniel.avila.ricknmortykmm.android.R
import daniel.avila.ricknmortykmm.android.features.CharacterDetailActivity.Companion.CHARACTER
import daniel.avila.ricknmortykmm.android.base.BaseActivity
import daniel.avila.ricknmortykmm.android.databinding.ActivityCharactersBinding
import daniel.avila.ricknmortykmm.android.model.mapper.CharacterMapper
import daniel.avila.ricknmortykmm.shared.base.executor.IExecutorScope
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersContract
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CharactersActivity : BaseActivity() /*, CharactersView*//*, INavigatorCharacters*/ {
    private val vm: CharactersViewModel by inject()
    private val mapper: CharacterMapper by inject()

    private val adapter: CharactersAdapter = CharactersAdapter {
        startActivity(Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(CHARACTER, mapper.map(it))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCharactersBinding.inflate(layoutInflater)
        binding.recycler.adapter = adapter
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        vm.setEvent(CharactersContract.Event.OnGetCharacters)

        launch {
            vm.uiState.collect {
                managementResourceState(it.characters) { characters ->
                    adapter.submitList(characters)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_favorite -> {
                startActivity(Intent(this, CharactersFavoriteActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun displayCharacters(characters: List<Character>) {
//        adapter.submitList(characters)
//    }

//    override fun navigateToDetail(character: Character) {
//        startActivity(Intent(this, DetailCharacterActivity::class.java).apply {
//            putExtra(CHARACTER, mapper.map(character))
//        })
//    }

//    override fun navigateToFavorites() {
//        startActivity(Intent(this, CharactersFavoriteActivity::class.java))
//    }

    override fun executor(): IExecutorScope = vm
}

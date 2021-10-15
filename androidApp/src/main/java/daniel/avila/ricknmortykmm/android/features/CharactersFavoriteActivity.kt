package daniel.avila.ricknmortykmm.android.features

import android.content.Intent
import android.os.Bundle
import android.view.View
import daniel.avila.ricknmortykmm.android.CharactersAdapter
import daniel.avila.ricknmortykmm.android.base.BaseActivity
import daniel.avila.ricknmortykmm.android.databinding.ActivityCharactersFavoritesBinding
import daniel.avila.ricknmortykmm.android.model.mapper.CharacterMapper
import daniel.avila.ricknmortykmm.shared.base.executor.IExecutorScope
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesContract
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CharactersFavoriteActivity :
    BaseActivity()/*, ICharactersFavoritesView, INavigatorCharactersFavorites*/ {
    private val vm: CharactersFavoritesViewModel by inject()
    private val mapper: CharacterMapper by inject()

    private var _binding: ActivityCharactersFavoritesBinding? = null
    private val binding get() = _binding!!

    private val adapter: CharactersAdapter = CharactersAdapter {
        startActivity(Intent(this, CharacterDetailActivity::class.java).apply {
            putExtra(CharacterDetailActivity.CHARACTER, mapper.map(it))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCharactersFavoritesBinding.inflate(layoutInflater)
        binding.recycler.adapter = adapter
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorites"
    }

    override fun onStart() {
        super.onStart()
        launch {
            vm.uiState.collect {
                managementResourceState(it.charactersFavorites) { charactersFavorites ->
                    binding.noFavorites.visibility = if (charactersFavorites.isEmpty()) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                    adapter.submitList(charactersFavorites)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.setEvent(CharactersFavoritesContract.Event.OnGetCharactersFavorites)
    }

//    override fun displayCharacters(characters: List<Character>) {
//        adapter.submitList(characters)
//    }
//
//    override fun navigateToDetail(character: Character) {
//        startActivity(Intent(this, DetailCharacterActivity::class.java).apply {
//            putExtra(DetailCharacterActivity.CHARACTER, mapper.map(character))
//        })
//    }

    override fun executor(): IExecutorScope = vm
}

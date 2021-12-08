package daniel.avila.ricknmortykmm.android.ui.features.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.ui.base.components.Empty
import daniel.avila.ricknmortykmm.android.ui.base.components.Error
import daniel.avila.ricknmortykmm.android.ui.base.components.Loading
import daniel.avila.ricknmortykmm.android.ui.features.characters.CharactersList
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesContract
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel
import org.koin.java.KoinJavaComponent

@ExperimentalCoilApi
@Composable
fun CharactersFavoriteScreen(
    onCharacterClick: (Int) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: CharactersFavoritesViewModel = KoinJavaComponent.get(CharactersFavoritesViewModel::class.java)
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ActionBar(onBackPressed = onBackPressed) }
    ) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (state.charactersFavorites) {
                BasicUiState.Empty -> {
                    Empty {
                        viewModel.setEvent(CharactersFavoritesContract.Event.OnGetCharactersFavorites)
                    }
                }
                is BasicUiState.Error -> {
                    Error {
                        viewModel.setEvent(CharactersFavoritesContract.Event.OnGetCharactersFavorites)
                    }
                }
                BasicUiState.Loading -> {
                    Loading()
                }
                BasicUiState.None -> {

                }
                is BasicUiState.Success -> {
                    CharactersList(
                        characters = (state.charactersFavorites as BasicUiState.Success<List<Character>>).data,
                        onCharacterClick = onCharacterClick
                    )
                }
            }
        }
    }
}
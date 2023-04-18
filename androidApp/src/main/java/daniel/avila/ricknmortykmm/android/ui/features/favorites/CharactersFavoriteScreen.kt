package daniel.avila.ricknmortykmm.android.ui.features.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.ui.components.state.ManagementResourceState
import daniel.avila.ricknmortykmm.android.ui.features.characters.CharactersList
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@Composable
fun CharactersFavoriteScreen(
    navController: NavController,
    onUiEvent: (CharactersFavoritesContract.Event) -> Unit,
    uiState: StateFlow<CharactersFavoritesContract.State>,
    uiEffect: Flow<CharactersFavoritesContract.Effect>,
    onCharacterDetailNavigate: (Int) -> Unit,
) {
    val state by uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = null) {
        uiEffect.collectLatest { effect ->
            when (effect) {
                is CharactersFavoritesContract.Effect.NavigateToDetailCharacter -> onCharacterDetailNavigate(effect.idCharacter)
                CharactersFavoritesContract.Effect.BackNavigation -> navController.popBackStack()
            }
        }
    }

    Scaffold(
        topBar = { ActionBar(onBackPressed = { onUiEvent(CharactersFavoritesContract.Event.OnBackPressed) }) }
    ) { padding ->
        ManagementResourceState(
            resourceState = state.charactersFavorites,
            successView = { favorites ->
                checkNotNull(favorites)
                CharactersList(
                    characters = favorites,
                    onCharacterClick = { idCharacter ->
                        onUiEvent(CharactersFavoritesContract.Event.OnCharacterClick(idCharacter))
                    }
                )
            },
            modifier = Modifier.padding(padding),
            onTryAgain = { onUiEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
            onCheckAgain = { onUiEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
            msgCheckAgain = "You don't favorite characters yet"
        )
    }
}
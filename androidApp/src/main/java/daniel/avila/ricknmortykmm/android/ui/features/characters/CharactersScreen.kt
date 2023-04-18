package daniel.avila.ricknmortykmm.android.ui.features.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.ui.components.CharacterItem
import daniel.avila.ricknmortykmm.android.ui.components.state.ManagementResourceState
import daniel.avila.ricknmortykmm.android.ui.navigation.NavItem
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@Composable
fun CharactersScreen(
    navController: NavController,
    onUiEvent: (CharactersContract.Event) -> Unit,
    uiState: StateFlow<CharactersContract.State>,
    uiEffect: Flow<CharactersContract.Effect>,
    onCharacterDetailNavigate: (Int) -> Unit
) {
    val state by uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = null) {
        uiEffect.collectLatest { effect ->
            when (effect) {
                is CharactersContract.Effect.NavigateToDetailCharacter -> onCharacterDetailNavigate(effect.idCharacter)
                CharactersContract.Effect.NavigateToFavorites -> navController.navigate(route = NavItem.Favorites.route)
            }
        }
    }

    Scaffold(
        topBar = { ActionBar { onUiEvent(CharactersContract.Event.OnFavoritesClick) } }
    ) { padding ->
        ManagementResourceState(
            resourceState = state.characters,
            successView = { characters ->
                checkNotNull(characters)
                CharactersList(
                    characters = characters,
                    onCharacterClick = { idCharacter ->
                        onUiEvent(CharactersContract.Event.OnCharacterClick(idCharacter))
                    }
                )
            },
            modifier = Modifier.padding(padding),
            onTryAgain = { onUiEvent(CharactersContract.Event.OnTryCheckAgainClick) },
            onCheckAgain = { onUiEvent(CharactersContract.Event.OnTryCheckAgainClick) },
        )
    }
}

@ExperimentalCoilApi
@Composable
fun CharactersList(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(characters) { character ->
            CharacterItem(
                character = character,
                onClick = { onCharacterClick(character.id) }
            )
        }
    }
}

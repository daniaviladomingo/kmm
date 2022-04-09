package daniel.avila.ricknmortykmm.android.ui.features.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.ui.components.CharacterItem
import daniel.avila.ricknmortykmm.android.ui.components.state.ManagementResourceState
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersContract
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel

@ExperimentalCoilApi
@Composable
fun CharactersScreen(
    onCharacterClick: (Int) -> Unit,
    navigateToFavorite: () -> Unit,
    viewModel: CharactersViewModel
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ActionBar(navigateToFavorite) }
    ) { padding ->
        ManagementResourceState(
            resourceState = state.characters,
            successView = { characters ->
                checkNotNull(characters)
                CharactersList(
                    characters = characters,
                    onCharacterClick = onCharacterClick
                )
            },
            modifier = Modifier.padding(padding),
            onTryAgain = { viewModel.setEvent(CharactersContract.Event.OnGetCharacters) },
            onCheckAgain = { viewModel.setEvent(CharactersContract.Event.OnGetCharacters) },
        )
    }
}

@ExperimentalCoilApi
@Composable
fun CharactersList(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit
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

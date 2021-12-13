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
import daniel.avila.ricknmortykmm.android.ui.base.components.CharacterItem
import daniel.avila.ricknmortykmm.android.ui.base.components.state.ManagementStateRequest
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersContract
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel
import org.koin.java.KoinJavaComponent.get

@ExperimentalCoilApi
@Composable
fun CharactersScreen(
    onCharacterClick: (Int) -> Unit,
    navigateToFavorite: () -> Unit,
    viewModel: CharactersViewModel = get(CharactersViewModel::class.java)
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ActionBar(navigateToFavorite) }
    ) { padding ->
        ManagementStateRequest(
            stateRequest = state.stateRequest,
            successView = {
                CharactersList(
                    characters = state.characters,
                    onCharacterClick = onCharacterClick
                )
            },
            onTryAgain = { viewModel.setEvent(CharactersContract.Event.OnGetCharacters) },
            onCheckAgain = { viewModel.setEvent(CharactersContract.Event.OnGetCharacters) },
            modifier = Modifier.padding(padding)
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

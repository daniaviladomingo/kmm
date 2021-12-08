package daniel.avila.ricknmortykmm.android.ui.features.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.ui.base.components.CharacterItem
import daniel.avila.ricknmortykmm.android.ui.base.components.Empty
import daniel.avila.ricknmortykmm.android.ui.base.components.Error
import daniel.avila.ricknmortykmm.android.ui.base.components.Loading
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersContract
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel
import org.koin.java.KoinJavaComponent.get

@ExperimentalCoilApi
@Composable
fun CharactersScreen(
    onCharacterClick: (Int) -> Unit,
    actionFavorite: () -> Unit,
    viewModel: CharactersViewModel = get(CharactersViewModel::class.java)
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { ActionBar(actionFavorite) }
    ) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (state.characters) {
                BasicUiState.Empty -> {
                    Empty {
                        viewModel.setEvent(CharactersContract.Event.OnGetCharacters)
                    }
                }
                is BasicUiState.Error -> {
                    Error {
                        viewModel.setEvent(CharactersContract.Event.OnGetCharacters)
                    }
                }
                BasicUiState.Loading -> {
                    Loading()
                }
                BasicUiState.None -> {

                }
                is BasicUiState.Success -> {
                    CharactersList(
                        characters = (state.characters as BasicUiState.Success<List<Character>>).data,
                        onCharacterClick = onCharacterClick
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CharactersList(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn {
        items(characters) { character ->
            CharacterItem(
                character = character,
                onClick = { onCharacterClick(character.id) }
            )
        }
    }
}

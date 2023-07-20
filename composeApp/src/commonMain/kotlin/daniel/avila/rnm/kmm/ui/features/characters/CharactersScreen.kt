package daniel.avila.rnm.kmm.ui.features.characters

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import daniel.avila.rnm.kmm.ui.common.ActionBarIcon
import daniel.avila.rnm.kmm.ui.common.CharactersList
import daniel.avila.rnm.kmm.ui.common.state.ManagementResourceState
import daniel.avila.rnm.kmm.ui.ext.getScreenModel
import daniel.avila.rnm.kmm.ui.features.character_detail.CharacterDetailScreen
import daniel.avila.rnm.kmm.ui.features.characters_favorites.CharactersFavoritesScreen
import kotlinx.coroutines.flow.collectLatest

class CharactersScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val charactersViewModel = getScreenModel<CharactersViewModel>()

        val state by charactersViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            charactersViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is CharactersContract.Effect.NavigateToDetailCharacter ->
                        navigator.push(CharacterDetailScreen(effect.idCharacter))
                    CharactersContract.Effect.NavigateToFavorites ->
                        navigator.push(CharactersFavoritesScreen())
                }
            }
        }

        Scaffold(
            topBar = { ActionAppBar { charactersViewModel.setEvent(CharactersContract.Event.OnFavoritesClick) } }
        ) { padding ->
            ManagementResourceState(
                resourceState = state.characters,
                successView = { characters ->
                    checkNotNull(characters)
                    CharactersList(
                        characters = characters,
                        onCharacterClick = { idCharacter ->
                            charactersViewModel.setEvent(CharactersContract.Event.OnCharacterClick(idCharacter))
                        }
                    )
                },
                modifier = Modifier.padding(padding),
                onTryAgain = { charactersViewModel.setEvent(CharactersContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { charactersViewModel.setEvent(CharactersContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

@Composable
fun ActionAppBar(
    onClickFavorite: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Rick & Morty KMM") },
        actions = {
            ActionBarIcon(
                onClick = onClickFavorite,
                icon = Icons.Filled.Favorite
            )
        }
    )
}



package daniel.avila.rnm.kmm.presentation.ui.features.characters_favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import daniel.avila.rnm.kmm.presentation.ui.common.ArrowBackIcon
import daniel.avila.rnm.kmm.presentation.ui.common.CharactersList
import daniel.avila.rnm.kmm.presentation.ui.common.state.ManagementResourceUiState
import daniel.avila.rnm.kmm.presentation.ui.features.character_detail.CharacterDetailScreen
import kotlinx.coroutines.flow.collectLatest

class CharactersFavoritesScreen : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val charactersFavoritesViewModel = getScreenModel<CharactersFavoritesViewModel>()

        val state by charactersFavoritesViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            charactersFavoritesViewModel.effect.collectLatest { effect ->
                when (effect) {
                    is CharactersFavoritesContract.Effect.NavigateToDetailCharacter ->
                        navigator.push(CharacterDetailScreen(effect.idCharacter))

                    CharactersFavoritesContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            topBar = {
                ActionBar(onBackPressed = {
                    charactersFavoritesViewModel.setEvent(
                        CharactersFavoritesContract.Event.OnBackPressed
                    )
                })
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.charactersFavorites,
                successView = { favorites ->
                    CharactersList(
                        characters = favorites,
                        onCharacterClick = { idCharacter ->
                            charactersFavoritesViewModel.setEvent(
                                CharactersFavoritesContract.Event.OnCharacterClick(
                                    idCharacter
                                )
                            )
                        }
                    )
                },
                onTryAgain = { charactersFavoritesViewModel.setEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { charactersFavoritesViewModel.setEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
                msgCheckAgain = "You don't have favorite characters yet"
            )
        }
    }
}

@Composable
fun ActionBar(
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Characters Favorites") },
        navigationIcon = { ArrowBackIcon(onBackPressed) }
    )
}
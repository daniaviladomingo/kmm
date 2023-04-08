package daniel.avila.ricknmortykmm.android.ui.features.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.ui.components.state.ManagementResourceState
import daniel.avila.ricknmortykmm.android.ui.features.characters.CharactersList
import daniel.avila.ricknmortykmm.android.ui.navigation.NavItem
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@Composable
fun CharactersFavoriteScreen(
    navController: NavController,
    onEvent: (CharactersFavoritesContract.Event) -> Unit,
    state: State<CharactersFavoritesContract.State>,
    effect: Flow<CharactersFavoritesContract.Effect>,
) {

    LaunchedEffect(key1 = null) {
        effect.collectLatest { effect ->
            when (effect) {
                is CharactersFavoritesContract.Effect.NavigateToDetailCharacter ->
                    navController.navigate(route = NavItem.Detail.createNavRoute(effect.idCharacter))
                CharactersFavoritesContract.Effect.BackNavigation -> navController.popBackStack()
            }
        }
    }

    Scaffold(
        topBar = { ActionBar(onBackPressed = { onEvent(CharactersFavoritesContract.Event.OnBackPressed) }) }
    ) { padding ->
        ManagementResourceState(
            resourceState = state.value.charactersFavorites,
            successView = { favorites ->
                checkNotNull(favorites)
                CharactersList(
                    characters = favorites,
                    onCharacterClick = { idCharacter ->
                        onEvent(CharactersFavoritesContract.Event.OnCharacterClick(idCharacter))
                    }
                )
            },
            modifier = Modifier.padding(padding),
            onTryAgain = { onEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
            onCheckAgain = { onEvent(CharactersFavoritesContract.Event.OnTryCheckAgainClick) },
            msgCheckAgain = "You don't favorite characters yet"
        )
    }
}
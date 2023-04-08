package daniel.avila.ricknmortykmm.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.ui.features.characters.CharactersScreen
import daniel.avila.ricknmortykmm.android.ui.features.detail.CharacterDetailScreen
import daniel.avila.ricknmortykmm.android.ui.features.favorites.CharactersFavoriteScreen
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailViewModel
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel

@ExperimentalCoilApi
@Composable
fun Navigation(
    vmCharacters: CharactersViewModel,
    vmCharacterDetail: CharacterDetailViewModel,
    vmCharactersFavorites: CharactersFavoritesViewModel,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        composable(NavItem.Characters) {
            CharactersScreen(
                navController = navController,
                onEvent = { event -> vmCharacters.setEvent(event) },
                state = vmCharacters.uiState.collectAsState(),
                effect = vmCharacters.effect
            )
        }
        composable(NavItem.Detail) { backStackEntry ->
            vmCharacterDetail.setEvent(
                CharacterDetailContract.Event.GetCharacter(
                    idCharacter = backStackEntry.findArg(NavArg.IdCharacter.key)
                )
            )
            CharacterDetailScreen(
                navController = navController,
                onEvent = { event -> vmCharacterDetail.setEvent(event) },
                state = vmCharacterDetail.uiState.collectAsState(),
                effect = vmCharacterDetail.effect
            )
        }
        composable(NavItem.Favorites) {
            CharactersFavoriteScreen(
                navController = navController,
                onEvent = { event -> vmCharactersFavorites.setEvent(event) },
                state = vmCharactersFavorites.uiState.collectAsState(),
                effect = vmCharactersFavorites.effect
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(key: String): T {
    val value = arguments?.get(key)
    requireNotNull(value)
    return value as T
}
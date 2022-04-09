package daniel.avila.ricknmortykmm.android.ui.navigation

import androidx.compose.runtime.Composable
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
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesContract
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel

@ExperimentalCoilApi
@Composable
fun Navigation(
    vmCharacters: CharactersViewModel,
    vmCharacterDetail: CharacterDetailViewModel,
    vmCharactersFavorites: CharactersFavoritesViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        composable(NavItem.Characters) {
            CharactersScreen(
                onCharacterClick = { idCharacter ->
                    navController.navigate(route = NavItem.Detail.route)
                    vmCharacterDetail.setEvent(
                        CharacterDetailContract.Event.GetCharacter(
                            idCharacter = idCharacter
                        )
                    )
                },
                navigateToFavorite = {
                    navController.navigate(route = NavItem.Favorites.route)
                },
                viewModel = vmCharacters
            )
        }
        composable(NavItem.Detail) {
//            backStackEntry.findArg(NavArg.CharacterId.key)
            CharacterDetailScreen(
                onBackPressed = { navController.popBackStack() },
                viewModel = vmCharacterDetail
            )
        }
        composable(NavItem.Favorites) {
            CharactersFavoriteScreen(
                onCharacterClick = { idCharacter ->
                    navController.navigate(route = NavItem.Detail.route)
                    vmCharacterDetail.setEvent(
                        CharacterDetailContract.Event.GetCharacter(
                            idCharacter = idCharacter
                        )
                    )
                },
                onBackPressed = { navController.popBackStack() },
                viewModel = vmCharactersFavorites
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
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
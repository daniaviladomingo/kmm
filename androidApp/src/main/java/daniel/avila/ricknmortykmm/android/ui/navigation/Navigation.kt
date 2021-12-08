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

@ExperimentalCoilApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        composable(NavItem.Characters) {
            CharactersScreen(
                onCharacterClick = { idCharacter ->
                    navController.navigate(route = NavItem.Detail.createNavRoute(idCharacter))
                },
                actionFavorite = {
                    navController.navigate(route = NavItem.Favorites.route)
                })
        }
        composable(NavItem.Detail) { backStackEntry ->
            CharacterDetailScreen(
                idCharacter = backStackEntry.findArg(NavArg.CharacterId.key),
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
        composable(NavItem.Favorites) {
            CharactersFavoriteScreen(
                onCharacterClick = { idCharacter ->
                    navController.navigate(route = NavItem.Detail.createNavRoute(idCharacter))
                },
                onBackPressed = {
                    navController.popBackStack()
                }
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
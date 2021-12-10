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
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailViewModel
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesContract
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel
import org.koin.java.KoinJavaComponent.get

@ExperimentalCoilApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    val vmCharacterDetail: CharacterDetailViewModel = get(CharacterDetailViewModel::class.java)
    val vmCharactersFavorites: CharactersFavoritesViewModel =
        get(CharactersFavoritesViewModel::class.java)

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
                    vmCharactersFavorites.setEvent(CharactersFavoritesContract.Event.OnGetCharactersFavorites)
                }
            )
        }
        composable(NavItem.Detail) {
//            backStackEntry.findArg(NavArg.CharacterId.key)
            CharacterDetailScreen(
                state = vmCharacterDetail.uiState.collectAsState(),
                effect = vmCharacterDetail.effect,
                onBackPressed = {
                    navController.popBackStack()
                },
                addFavorite = {
                    vmCharacterDetail.setEvent(CharacterDetailContract.Event.AddCharacterToFavorite)
                    vmCharactersFavorites.setEvent(CharactersFavoritesContract.Event.OnGetCharactersFavorites)
                },
                removeFavorite = {
                    vmCharacterDetail.setEvent(CharacterDetailContract.Event.RemoveCharacterToFavorite)
                    vmCharactersFavorites.setEvent(CharactersFavoritesContract.Event.OnGetCharactersFavorites)
                },
                retry = {
                    vmCharacterDetail.setEvent(CharacterDetailContract.Event.Retry)
                }
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
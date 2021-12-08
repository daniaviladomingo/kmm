package daniel.avila.ricknmortykmm.android.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Characters : NavItem("characters")
    object Detail : NavItem(
        "detail",
        listOf(
            NavArg.CharacterId
        )
    ) {
        fun createNavRoute(characterId: Int) = "$baseRoute/$characterId"
    }

    object Favorites : NavItem("favorites")
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    CharacterId("id", NavType.IntType)

}
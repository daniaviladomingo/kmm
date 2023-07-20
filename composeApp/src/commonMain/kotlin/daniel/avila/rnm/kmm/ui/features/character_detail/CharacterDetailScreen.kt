package daniel.avila.rnm.kmm.ui.features.character_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberAsyncImagePainter
import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.domain.model.Status
import daniel.avila.rnm.kmm.ui.common.ActionBarIcon
import daniel.avila.rnm.kmm.ui.common.ArrowBackIcon
import daniel.avila.rnm.kmm.ui.common.state.ManagementResourceState
import daniel.avila.rnm.kmm.ui.ext.getScreenModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf

class CharacterDetailScreen(
    private val characterId: Int,
) : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val characterDetailViewModel = getScreenModel<CharacterDetailViewModel> { parametersOf(characterId) }

        val state by characterDetailViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        var name by remember { mutableStateOf("") }

        LaunchedEffect(key1 = Unit) {
            characterDetailViewModel.effect.collectLatest { effect ->
                when (effect) {
                    CharacterDetailContract.Effect.CharacterAdded ->
                        scaffoldState.snackbarHostState.showSnackbar("Character added to favorites")

                    CharacterDetailContract.Effect.CharacterRemoved ->
                        scaffoldState.snackbarHostState.showSnackbar("Character removed from favorites")

                    CharacterDetailContract.Effect.BackNavigation -> navigator.pop()
                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ActionBar(
                    name = name,
                    isFavorite = state.isFavorite,
                    actionAddFavorite = { characterDetailViewModel.setEvent(CharacterDetailContract.Event.OnAddCharacterToFavoriteClick) },
                    actionRemoveFavorite = {
                        characterDetailViewModel.setEvent(
                            CharacterDetailContract.Event.OnRemoveCharacterFromFavoriteClick
                        )
                    },
                    onBackPressed = { characterDetailViewModel.setEvent(CharacterDetailContract.Event.OnBackPressed) }
                )
            }
        ) { padding ->
            ManagementResourceState(
                resourceState = state.character,
                successView = { character ->
                    requireNotNull(character)
                    CharacterDetail(character)
                    name = character.name
                },
                modifier = Modifier.padding(padding),
                onTryAgain = { characterDetailViewModel.setEvent(CharacterDetailContract.Event.OnTryCheckAgainClick) },
                onCheckAgain = { characterDetailViewModel.setEvent(CharacterDetailContract.Event.OnTryCheckAgainClick) },
            )
        }
    }
}

@Composable
fun CharacterDetail(character: Character) {
    val status = character.status
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.size(10.dp))
        Image(
            modifier = Modifier.size(200.dp),
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "${character.origin}, ${character.species}",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = status.name, color = when (status) {
                Status.ALIVE -> Color.Green
                Status.DEAD -> Color.Red
                Status.UNKNOWN -> Color.Gray
            },
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ActionBar(
    name: String,
    isFavorite: Boolean,
    actionAddFavorite: () -> Unit,
    actionRemoveFavorite: () -> Unit,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = name) },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        actions = {
            if (isFavorite) {
                ActionBarIcon(
                    onClick = actionRemoveFavorite,
                    icon = Icons.Filled.Favorite
                )
            } else {
                ActionBarIcon(
                    onClick = actionAddFavorite,
                    icon = Icons.Filled.FavoriteBorder
                )
            }
        }
    )
}
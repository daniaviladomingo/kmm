package daniel.avila.rnm.kmm.presentation.ui.features.character_detail

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberAsyncImagePainter
import daniel.avila.rnm.kmm.domain.model.Character
import daniel.avila.rnm.kmm.domain.model.Status
import daniel.avila.rnm.kmm.presentation.ui.common.ActionBarIcon
import daniel.avila.rnm.kmm.presentation.ui.common.ArrowBackIcon
import daniel.avila.rnm.kmm.presentation.ui.common.state.ManagementResourceUiState
import daniel.avila.rnm.kmm.presentation.model.ResourceUiState
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.parametersOf

class CharacterDetailScreen(
    private val characterId: Int,
) : Screen {
    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val characterDetailViewModel =
            getScreenModel<CharacterDetailViewModel> { parametersOf(characterId) }

        val state by characterDetailViewModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

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
                    character = state.character,
                    favorite = state.isFavorite,
                    onActionFavorite = { characterDetailViewModel.setEvent(CharacterDetailContract.Event.OnFavoriteClick) },
                    onBackPressed = { characterDetailViewModel.setEvent(CharacterDetailContract.Event.OnBackPressed) }
                )
            }
        ) { padding ->
            ManagementResourceUiState(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                resourceUiState = state.character,
                successView = { character ->
                    CharacterDetail(character)
                },
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
    character: ResourceUiState<Character>,
    favorite: ResourceUiState<Boolean>,
    onActionFavorite: () -> Unit,
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        title = {
            ManagementResourceUiState(
                resourceUiState = character,
                successView = { Text(text = it.name) },
                loadingView = { Text(text = "....") },
                onCheckAgain = {},
                onTryAgain = {}
            )
        },
        navigationIcon = { ArrowBackIcon(onBackPressed) },
        actions = {
            ManagementResourceUiState(
                resourceUiState = favorite,
                successView = {
                    ActionBarIcon(
                        onClick = onActionFavorite,
                        icon = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                    )
                },
                loadingView = {
                    ActionBarIcon(
                        enabled = false,
                        onClick = {},
                        icon = Icons.Filled.Favorite
                    )
                },
                onCheckAgain = {},
                onTryAgain = {}
            )
        }
    )
}
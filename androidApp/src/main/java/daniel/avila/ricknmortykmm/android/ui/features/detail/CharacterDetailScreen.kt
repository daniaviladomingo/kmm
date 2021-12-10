package daniel.avila.ricknmortykmm.android.ui.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import daniel.avila.ricknmortykmm.android.ui.base.components.state.Empty
import daniel.avila.ricknmortykmm.android.ui.base.components.state.Error
import daniel.avila.ricknmortykmm.android.ui.base.components.state.Loading
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.Status
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    state: State<CharacterDetailContract.State>,
    effect: Flow<CharacterDetailContract.Effect>,
    onBackPressed: () -> Unit,
    addFavorite: () -> Unit,
    removeFavorite: () -> Unit,
    retry: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        effect.collectLatest { effect ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = when (effect) {
                    CharacterDetailContract.Effect.CharacterAdded -> "Character added to favorites"
                    CharacterDetailContract.Effect.CharacterRemoved -> "Character removed from favorites"
                }
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ActionBar(
                name = name,
                isFavorite = state.value.isFavorite,
                actionAddFavorite = addFavorite,
                actionRemoveFavorite = removeFavorite,
                onBackPressed = onBackPressed
            )
        }
    ) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (state.value.character) {
                BasicUiState.Empty -> {
                    Empty {
                        retry()
                    }
                }
                is BasicUiState.Error -> {
                    Error {
                        retry()
                    }
                }
                BasicUiState.Loading -> {
                    Loading()
                }
                BasicUiState.None -> {

                }
                is BasicUiState.Success -> {
                    val character = (state.value.character as BasicUiState.Success<Character>).data
                    CharacterDetail(character)
                    name = character.name
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CharacterDetail(character: Character) {
    val status = character.status
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.size(10.dp))
        Image(
            modifier = Modifier.size(200.dp),
            painter = rememberImagePainter(character.image),
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
package daniel.avila.ricknmortykmm.android.ui.features.detail

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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import daniel.avila.ricknmortykmm.android.ui.components.state.ManagementResourceState
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.Status
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    navController: NavController,
    onUiEvent: (CharacterDetailContract.Event) -> Unit,
    uiState: StateFlow<CharacterDetailContract.State>,
    uiEffect: Flow<CharacterDetailContract.Effect>,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    val state by uiState.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        uiEffect.collectLatest { effect ->
            when (effect) {
                CharacterDetailContract.Effect.CharacterAdded ->
                    scaffoldState.snackbarHostState.showSnackbar("Character added to favorites")
                CharacterDetailContract.Effect.CharacterRemoved ->
                    scaffoldState.snackbarHostState.showSnackbar("Character removed from favorites")
                CharacterDetailContract.Effect.BackNavigation -> navController.popBackStack()
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ActionBar(
                name = name,
                isFavorite = state.isFavorite,
                actionAddFavorite = { onUiEvent(CharacterDetailContract.Event.OnAddCharacterToFavoriteClick) },
                actionRemoveFavorite = { onUiEvent(CharacterDetailContract.Event.OnRemoveCharacterFromFavoriteClick) },
                onBackPressed = { onUiEvent(CharacterDetailContract.Event.OnBackPressed) }
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
            onTryAgain = { onUiEvent(CharacterDetailContract.Event.OnTryCheckAgainClick) },
            onCheckAgain = { onUiEvent(CharacterDetailContract.Event.OnTryCheckAgainClick) },
        )
    }
}

@ExperimentalCoilApi
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
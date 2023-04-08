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
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import daniel.avila.ricknmortykmm.android.ui.components.state.ManagementResourceState
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.Status
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    navController: NavController,
    onEvent: (CharacterDetailContract.Event) -> Unit,
    state: State<CharacterDetailContract.State>,
    effect: Flow<CharacterDetailContract.Effect>,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    var name by remember { mutableStateOf("") }

    LaunchedEffect(key1 = null) {
        effect.collectLatest { effect ->
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
                isFavorite = state.value.isFavorite,
                actionAddFavorite = { onEvent(CharacterDetailContract.Event.OnAddCharacterToFavoriteClick) },
                actionRemoveFavorite = { onEvent(CharacterDetailContract.Event.OnRemoveCharacterFromFavoriteClick) },
                onBackPressed = { onEvent(CharacterDetailContract.Event.OnBackPressed) }
            )
        }
    ) { padding ->
        ManagementResourceState(
            resourceState = state.value.character,
            successView = { character ->
                requireNotNull(character)
                CharacterDetail(character)
                name = character.name
            },
            modifier = Modifier.padding(padding),
            onTryAgain = { onEvent(CharacterDetailContract.Event.OnTryCheckAgainClick) },
            onCheckAgain = { onEvent(CharacterDetailContract.Event.OnTryCheckAgainClick) },
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
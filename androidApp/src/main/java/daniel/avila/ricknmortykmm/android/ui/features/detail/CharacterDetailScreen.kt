package daniel.avila.ricknmortykmm.android.ui.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import daniel.avila.ricknmortykmm.android.ui.base.components.Empty
import daniel.avila.ricknmortykmm.android.ui.base.components.Error
import daniel.avila.ricknmortykmm.android.ui.base.components.Loading
import daniel.avila.ricknmortykmm.shared.base.mvi.BasicUiState
import daniel.avila.ricknmortykmm.shared.domain.model.Character
import daniel.avila.ricknmortykmm.shared.domain.model.Status
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailContract
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailViewModel
import org.koin.java.KoinJavaComponent.get

@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    idCharacter: Int,
    onBackPressed: () -> Unit,
    viewModel: CharacterDetailViewModel = get(CharacterDetailViewModel::class.java),
) {
    val state by viewModel.uiState.collectAsState()

    viewModel.setEvent(CharacterDetailContract.Event.GetCharacter(idCharacter))

    Scaffold(
        topBar = {
            ActionBar(
                actionFavorite = {},
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
            when (state.character) {
                BasicUiState.Empty -> {
                    Empty {
                        viewModel.setEvent(CharacterDetailContract.Event.GetCharacter(idCharacter = idCharacter))
                    }
                }
                is BasicUiState.Error -> {
                    Error {
                        viewModel.setEvent(CharacterDetailContract.Event.GetCharacter(idCharacter = idCharacter))
                    }
                }
                BasicUiState.Loading -> {
                    Loading()
                }
                BasicUiState.None -> {

                }
                is BasicUiState.Success -> {
                    CharacterDetail((state.character as BasicUiState.Success<Character>).data)
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
package daniel.avila.ricknmortykmm.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import coil.annotation.ExperimentalCoilApi
import daniel.avila.ricknmortykmm.android.base.BaseActivity
import daniel.avila.ricknmortykmm.android.ui.navigation.Navigation
import daniel.avila.ricknmortykmm.android.ui.theme.RickNMortyKmmTheme
import daniel.avila.ricknmortykmm.shared.base.executor.IExecutorScope
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailViewModel
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel
import org.koin.android.ext.android.inject

@ExperimentalCoilApi
class MainActivity : BaseActivity() {

    private val vmCharacters: CharactersViewModel by inject()
    private val vmCharacterDetail: CharacterDetailViewModel by inject()
    private val vmCharactersFavorites: CharactersFavoritesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickNMortyKmmTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(
                        vmCharacters = vmCharacters,
                        vmCharacterDetail = vmCharacterDetail,
                        vmCharactersFavorites = vmCharactersFavorites
                    )
                }
            }
        }
    }

    override val vm: Array<IExecutorScope>
        get() = arrayOf(vmCharacters, vmCharacterDetail, vmCharactersFavorites)
}



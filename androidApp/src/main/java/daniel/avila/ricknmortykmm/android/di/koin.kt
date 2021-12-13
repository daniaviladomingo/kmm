package daniel.avila.ricknmortykmm.android.di

import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailViewModel
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { CharactersViewModel() }
    single { CharactersFavoritesViewModel() }
    single { CharacterDetailViewModel() }
}
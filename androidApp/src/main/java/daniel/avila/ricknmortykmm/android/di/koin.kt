package daniel.avila.ricknmortykmm.android.di

import daniel.avila.ricknmortykmm.android.model.mapper.CharacterMapper
import daniel.avila.ricknmortykmm.shared.features.characters.mvi.CharactersViewModel
import daniel.avila.ricknmortykmm.shared.features.detail.mvi.CharacterDetailViewModel
import daniel.avila.ricknmortykmm.shared.features.favorites.mvi.CharactersFavoritesViewModel
import org.koin.dsl.module

val mapperModule = module {
    factory { CharacterMapper() }
}

val viewModelModule = module {
    factory { CharactersViewModel() }
    factory { CharactersFavoritesViewModel() }
    factory { CharacterDetailViewModel() }
}
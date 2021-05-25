package daniel.avila.ricknmortykmm.androidApp.di

import daniel.avila.ricknmortykmm.androidApp.model.mapper.CharacterMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { CharacterMapper() }
}
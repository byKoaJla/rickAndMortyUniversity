package by.koajla.character_episodes.di

import by.koajla.character_episodes.repository.CharacterWithEpisodesRepositoryImpl
import by.koajla.episodes.domain.repository.CharacterWithEpisodesRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val dataCharacterEpisodesModule = module {
    single { CharacterWithEpisodesRepositoryImpl(get()) }.bind(CharacterWithEpisodesRepository::class)
}
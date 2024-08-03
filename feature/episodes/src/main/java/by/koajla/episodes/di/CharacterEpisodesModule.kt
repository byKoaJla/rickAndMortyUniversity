package by.koajla.episodes.di

import by.koajla.episodes.domain.use_case.GetCharacterWithEpisodesUseCase
import by.koajla.episodes.presentation.CharacterEpisodesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val characterEpisodesModule = module {
    factory { GetCharacterWithEpisodesUseCase(get()) }
    viewModelOf(::CharacterEpisodesViewModel)
}
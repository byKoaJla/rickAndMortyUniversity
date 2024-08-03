package by.koajla.characters.di

import by.koajla.characters.domain.use_case.GetCharactersUseCase
import by.koajla.characters.presentation.CharactersViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val charactersModule = module {
    factory { GetCharactersUseCase(get()) }
    singleOf(::CharactersViewModel)
}
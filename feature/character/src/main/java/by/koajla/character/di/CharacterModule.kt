package by.koajla.character.di

import by.koajla.character.domain.use_case.GetCharacterUseCase
import by.koajla.character.presentation.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val characterModule = module {
    factory { GetCharacterUseCase(get()) }
    viewModelOf(::CharacterViewModel)
}
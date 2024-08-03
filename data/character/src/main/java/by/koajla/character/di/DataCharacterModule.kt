package by.koajla.character.di

import by.koajla.character.domain.repository.CharacterRepository
import by.koajla.character.repository.CharacterRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val dataCharacterModule = module {
    single { CharacterRepositoryImpl(get()) }.bind(CharacterRepository::class)
}
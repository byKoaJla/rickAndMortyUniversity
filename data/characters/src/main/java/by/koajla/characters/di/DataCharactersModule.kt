package by.koajla.characters.di

import by.koajla.characters.domain.repository.CharactersRepository
import by.koajla.characters.repository.CharactersRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val dataCharactersModule = module {
    single { CharactersRepositoryImpl(get(), context = get()) }.bind(CharactersRepository::class)
}
package by.koajla.network

import org.koin.dsl.module

val networkModule = module {
    single { RickAndMortyClient(get()) }
}
package by.koajla.rmuniversity

import org.koin.dsl.module

val appModule = module {
    single { "https://rickandmortyapi.com/graphql" }
}
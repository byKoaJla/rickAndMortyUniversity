package by.koajla.rmuniversity

import by.koajla.character.di.dataCharacterModule
import by.koajla.character_episodes.di.dataCharacterEpisodesModule
import by.koajla.characters.di.dataCharactersModule
import by.koajla.network.networkModule
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules



class KoinModulesVerifyTest : KoinTest {
    @Test
    fun verifyKoinApp() {
        koinApplication {
            modules(appModule, networkModule, dataCharacterModule, dataCharacterEpisodesModule)
            checkModules()
        }
    }
}
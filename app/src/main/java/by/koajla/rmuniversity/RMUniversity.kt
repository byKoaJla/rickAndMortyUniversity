package by.koajla.rmuniversity

import android.app.Application
import by.koajla.character.di.characterModule
import by.koajla.character.di.dataCharacterModule
import by.koajla.character_episodes.di.dataCharacterEpisodesModule
import by.koajla.characters.di.charactersModule
import by.koajla.characters.di.dataCharactersModule
import by.koajla.episodes.di.characterEpisodesModule
import by.koajla.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RMUniversity : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RMUniversity)
            androidLogger(level = Level.INFO)
            modules(
                appModule,
                networkModule,
                dataCharacterModule,
                dataCharacterEpisodesModule,
                dataCharactersModule,
                characterModule,
                characterEpisodesModule,
                charactersModule
            )
        }
    }
}
package by.koajla.character_episodes.repository

import by.koajla.character_episodes.mapper.CharacterEpisodesMapper.toCharacterEpisodes
import by.koajla.common.ApiCallResult
import by.koajla.episodes.domain.model.CharacterWithEpisodes
import by.koajla.episodes.domain.repository.CharacterWithEpisodesRepository
import by.koajla.network.RickAndMortyClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CharacterWithEpisodesRepositoryImpl(
    private val client: RickAndMortyClient
) : CharacterWithEpisodesRepository {
    override fun getCharacterWithEpisodes(id: Int): Flow<ApiCallResult<CharacterWithEpisodes>> = callbackFlow {
        client.getEpisodesWithCharacter(id)
            .collect { result ->
                result.onSuccess { data ->
                    trySend(ApiCallResult.Ok(data.toCharacterEpisodes()))
                }
                    .onFailure { err ->
                        trySend(ApiCallResult.Err(err))
                    }
            }
        awaitClose {  }
    }
}
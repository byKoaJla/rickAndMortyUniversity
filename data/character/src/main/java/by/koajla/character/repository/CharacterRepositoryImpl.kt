package by.koajla.character.repository

import by.koajla.character.domain.model.Character
import by.koajla.character.domain.repository.CharacterRepository
import by.koajla.character.mapper.CharacterMapper.toCharacter
import by.koajla.common.ApiCallResult
import by.koajla.network.RickAndMortyClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CharacterRepositoryImpl(
    private val client: RickAndMortyClient
) : CharacterRepository {
    override fun getCharacter(id: Int): Flow<ApiCallResult<Character>> = callbackFlow {
        client.getCharacter(id)
            .collect { data ->
                data.onSuccess { character ->
                    trySend(ApiCallResult.Ok(character.toCharacter()))
                }
                    .onFailure { err ->
                        trySend(ApiCallResult.Err(err))
                    }
            }

        awaitClose {  }
    }
}
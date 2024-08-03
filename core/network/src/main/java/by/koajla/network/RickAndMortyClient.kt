package by.koajla.network

import by.koajla.common.ApiCallResult
import com.apollographql.apollo.ApolloClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RickAndMortyClient(url: String): RickAndMortyApi {
    private val client = ApolloClient
        .Builder()
        .serverUrl(url)
        .build()

    private val characterCache = mutableMapOf<Int, CharacterQuery.Character>()

    override fun getCharacter(id: Int): Flow<ApiCallResult<CharacterQuery.Character>> = flow {
        characterCache[id]?.let { emit(ApiCallResult.Ok(it)); return@flow }
        client.query(CharacterQuery(id.toString()))
            .toFlow()
            .catch { err ->
                emit(ApiCallResult.Err(err))
            }
            .collect { result ->
                emit(ApiCallResult.Ok(result.data!!.character!!))
            }
    }

    override fun getEpisodesWithCharacter(id: Int): Flow<ApiCallResult<EpisodesWithCharacterQuery.Character>> = flow {
        client.query(EpisodesWithCharacterQuery(id.toString()))
            .toFlow()
            .catch { err -> emit(ApiCallResult.Err(err)) }
            .collect { result ->
                emit(ApiCallResult.Ok(result.data!!.character!!))
            }
    }

    override fun getAllCharactersWithPage(page: Int): Flow<ApiCallResult<GetCharactersWithPageQuery.Characters>> = flow {
        client.query(GetCharactersWithPageQuery(page))
            .toFlow()
            .catch { err -> emit(ApiCallResult.Err(err)) }
            .collect {result ->
                emit(ApiCallResult.Ok(result.data!!.characters!!))
            }
    }
}

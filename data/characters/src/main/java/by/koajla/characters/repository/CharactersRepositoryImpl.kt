package by.koajla.characters.repository

import android.content.Context
import android.util.Log
import by.koajla.characters.domain.model.Characters
import by.koajla.characters.domain.repository.CharactersRepository
import by.koajla.characters.mapper.CharactersMapper.toCharacters
import by.koajla.characters.mapper.PageInfoSerializer.pageStore
import by.koajla.common.ApiCallResult
import by.koajla.network.RickAndMortyClient
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.security.InvalidParameterException

class CharactersRepositoryImpl(
    private val client: RickAndMortyClient,
    private val context: Context,
) : CharactersRepository {
    override fun getCharactersByPage(page: Int): Flow<ApiCallResult<List<Characters>>> = callbackFlow {
        launch {
            context.pageStore.data.collect { p ->
                Log.d("DataStore", "getCharactersByPage: ${p.page} | $page")
                if (p.page in 1..<page) {
                    throw InvalidParameterException("error")
                }
            }
        }
        client.getAllCharactersWithPage(page)
            .collect {data ->
                data.onSuccess { characters ->
                    launch {
                        val pData = context.pageStore.data.firstOrNull() ?: return@launch
                        if(pData.page == 0) {
                            context.pageStore.updateData { p ->
                                p.toBuilder().setPage(characters.info!!.pages!!).build()
                            }
                        }
                    }
                    trySend(ApiCallResult.Ok(characters.results!!.map { it!!.toCharacters() }))
                }
                data.onFailure { err ->
                    trySend(ApiCallResult.Err(err))
                }
            }
        awaitClose {  }
    }
}
package by.koajla.characters.domain.repository

import android.content.Context
import by.koajla.characters.domain.model.Characters
import by.koajla.common.ApiCallResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharactersByPage(page: Int): Flow<ApiCallResult<List<Characters>>>
}
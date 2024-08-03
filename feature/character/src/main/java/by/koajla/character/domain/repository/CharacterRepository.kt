package by.koajla.character.domain.repository

import by.koajla.character.domain.model.Character
import by.koajla.common.ApiCallResult
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacter(id: Int): Flow<ApiCallResult<Character>>
}
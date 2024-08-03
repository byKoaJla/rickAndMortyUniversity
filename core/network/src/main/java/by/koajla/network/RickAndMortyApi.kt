package by.koajla.network

import androidx.annotation.IntRange
import by.koajla.common.ApiCallResult
import kotlinx.coroutines.flow.Flow

interface RickAndMortyApi {
    fun getCharacter(id: Int) : Flow<ApiCallResult<CharacterQuery.Character>>
    fun getEpisodesWithCharacter(id: Int): Flow<ApiCallResult<EpisodesWithCharacterQuery.Character>>
    fun getAllCharactersWithPage(
        @IntRange(1, 42) page: Int = 1
    ): Flow<ApiCallResult<GetCharactersWithPageQuery.Characters>>
}
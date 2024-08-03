package by.koajla.episodes.domain.repository

import by.koajla.common.ApiCallResult
import by.koajla.episodes.domain.model.CharacterWithEpisodes
import kotlinx.coroutines.flow.Flow

interface CharacterWithEpisodesRepository {
    fun getCharacterWithEpisodes(id: Int): Flow<ApiCallResult<CharacterWithEpisodes>>
}
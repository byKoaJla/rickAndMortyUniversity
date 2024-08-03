package by.koajla.episodes.domain.use_case

import by.koajla.episodes.domain.repository.CharacterWithEpisodesRepository

class GetCharacterWithEpisodesUseCase(
    private val repository: CharacterWithEpisodesRepository
) {
    operator fun invoke(id: Int) = repository.getCharacterWithEpisodes(id)
}
package by.koajla.characters.domain.use_case

import by.koajla.characters.domain.repository.CharactersRepository

class GetCharactersUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(page: Int = 1) = repository.getCharactersByPage(page)
}
package by.koajla.character.domain.use_case

import by.koajla.character.domain.repository.CharacterRepository

class GetCharacterUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.getCharacter(id)
}
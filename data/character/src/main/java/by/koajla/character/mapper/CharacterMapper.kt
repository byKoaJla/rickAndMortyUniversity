package by.koajla.character.mapper

import by.koajla.character.domain.model.Character
import by.koajla.character.domain.model.Episode
import by.koajla.character.domain.model.Gender
import by.koajla.character.domain.model.Status
import by.koajla.network.CharacterQuery

object CharacterMapper {
    fun CharacterQuery.Character.toCharacter() = Character(
        id = id!!.toInt(),
        name = name!!,
        image = image!!,
        status = when(status?.lowercase()) {
            "alive" -> Status.Alive
            "dead" -> Status.Dead
            else -> Status.Unknown
        },
        species = species!!,
        type = type!!,
        gender = when(gender?.lowercase()) {
            "female" -> Gender.Female
            "male" -> Gender.Male
            "genderless" -> Gender.Genderless
            else -> Gender.Unknown
        },
        origin = origin!!.name!!,
        location = location!!.name!!,
        episodes = episode
            .map { episode -> Episode(episode!!.name!!, episode!!.episode!!) },
        created = created!!
    )
}
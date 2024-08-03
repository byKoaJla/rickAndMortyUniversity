package by.koajla.characters.mapper

import by.koajla.characters.domain.model.Characters
import by.koajla.characters.domain.model.Status
import by.koajla.network.GetCharactersWithPageQuery


object CharactersMapper {
    fun GetCharactersWithPageQuery.Result.toCharacters() = Characters(
        id = id!!.toInt(),
        name = name!!,
        status = when(status!!.lowercase()) {
            "alive" -> Status.Alive
            "dead" -> Status.Dead
            else -> Status.Unknown
        },
        location = location!!.name!!,
        episode = episode[0]!!.name!!,
        species = species!!,
        image = image!!
    )
}
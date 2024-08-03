package by.koajla.character_episodes.mapper

import android.view.KeyCharacterMap.UnavailableException
import by.koajla.episodes.domain.model.CharacterWithEpisodes
import by.koajla.episodes.domain.model.Episode
import by.koajla.network.EpisodesWithCharacterQuery

object CharacterEpisodesMapper {
    fun EpisodesWithCharacterQuery.Character.toCharacterEpisodes() = CharacterWithEpisodes(
        id = id!!.toInt(),
        name = name!!,
        image = image!!,
        episodes = convertEpisodesToMap(episodes = episode)
    )

    private fun EpisodesWithCharacterQuery.Episode.toEpisode() = Episode(
        episode = episode!!,
        name = name!!,
        airDate = air_date!!
    )

    private fun convertEpisodesToMap(episodes: List<EpisodesWithCharacterQuery.Episode?>): Map<Int, List<Episode>> {
        if (episodes.isEmpty()) throw UnavailableException("List is empty")
        else {
            return episodes.groupBy(
                keySelector = { episode -> episode!!.episode!!.filter { it.isDigit() }.take(2).toInt() },
                valueTransform = { it!!.toEpisode() }
            )
        }
    }
}
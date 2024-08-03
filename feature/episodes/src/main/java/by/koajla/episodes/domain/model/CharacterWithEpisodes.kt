package by.koajla.episodes.domain.model

data class CharacterWithEpisodes(
    val id: Int,
    val name: String,
    val image: String,
    val episodes: Map<Int, List<Episode>>
)


package by.koajla.character.domain.model

data class Character(
    val id          : Int,
    val name        : String,
    val image       : String,
    val status      : Status,
    val species     : String?,
    val type        : String,
    val gender      : Gender,
    val origin      : String?,
    val location    : String?,
    val episodes    : List<Episode>,
    val created     : String
)




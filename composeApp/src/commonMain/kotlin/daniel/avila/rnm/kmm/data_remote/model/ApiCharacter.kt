package daniel.avila.rnm.kmm.data_remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiCharactersResponse(
    val results: List<ApiCharacter>
)

@Serializable
data class ApiCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String
)

@Serializable
data class Location(
    val name: String
)

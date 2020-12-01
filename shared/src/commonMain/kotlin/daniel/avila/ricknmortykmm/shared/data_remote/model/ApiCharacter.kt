package daniel.avila.ricknmortykmm.shared.data_remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiCharactersResponse(
    val results: List<ApiCharacter>
)

@Serializable
data class ApiCharacter(
    val id: Int,
    val name: String,
    val image: String
)
package daniel.avila.ricknmortykmm.android.model

import android.os.Parcelable
import daniel.avila.ricknmortykmm.shared.domain.model.Gender
import daniel.avila.ricknmortykmm.shared.domain.model.Status
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterParcelable (
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val gender: Gender,
    val origin: String,
    val location: String,
    val image: String
): Parcelable
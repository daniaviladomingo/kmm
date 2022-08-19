package daniel.avila.ricknmortykmm.shared.domain.model.core

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Exception) : Resource<Nothing>()
}
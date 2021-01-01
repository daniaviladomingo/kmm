package daniel.avila.ricknmortykmm.shared.data_cache

import daniel.avila.ricknmortykmm.shared.repository.ICacheData

expect class CacheDataFactory {
    fun createCacheData(): ICacheData
}
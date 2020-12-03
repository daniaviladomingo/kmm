package daniel.avila.ricknmortykmm.shared

import daniel.avila.ricknmortykmm.shared.data_remote.RemoteDataImp
import daniel.avila.ricknmortykmm.shared.repository.model.mapper.ApiCharacterMapper
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

//val dataRemote = RemoteDataImp(HttpClient {
//    install(JsonFeature) {
//        val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
//        serializer = KotlinxSerializer(json)
//    }
//})
//
//val apiCharacterMapper = ApiCharacterMapper()

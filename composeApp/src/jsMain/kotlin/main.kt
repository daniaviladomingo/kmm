import daniel.avila.rnm.kmm.App
import daniel.avila.rnm.kmm.di.initKoin
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    initKoin {}
    onWasmReady {
        BrowserViewportWindow("Rick N Morty KMM") {
            App()
        }
    }
}

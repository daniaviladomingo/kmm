import daniel.avila.rnm.kmm.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Rick N Morty KMM") {
            App()
        }
    }
}

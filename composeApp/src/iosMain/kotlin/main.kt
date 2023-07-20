import androidx.compose.ui.window.ComposeUIViewController
import daniel.avila.rnm.kmm.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}

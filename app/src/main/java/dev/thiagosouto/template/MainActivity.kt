package dev.thiagosouto.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.google.maps.android.compose.GoogleMap
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.maps.GoogleMapOptions
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import dagger.hilt.android.AndroidEntryPoint
import dev.thiagosouto.template.feature.presentation.FeatureViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: FeatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                setContent {
                    Column {
                        Text(
                            "Hello, world!",
                            style = TextStyle(fontSize = 30.sp)
                        )

                        var mapProperties by remember {
                            mutableStateOf(
                                MapProperties(maxZoomPreference = 10f, minZoomPreference = 5f)
                            )
                        }
                        var mapUiSettings by remember {
                            mutableStateOf(
                                MapUiSettings(mapToolbarEnabled = false)
                            )
                        }
                        Box(Modifier.fillMaxSize()) {
                            GoogleMap(properties = mapProperties, uiSettings = mapUiSettings)
                            Column {
                                Button(onClick = {
                                    mapProperties = mapProperties.copy(
                                        isBuildingEnabled = !mapProperties.isBuildingEnabled
                                    )
                                }) {
                                    Text(text = "Toggle isBuildingEnabled")
                                }
                                Button(onClick = {
                                    mapUiSettings = mapUiSettings.copy(
                                        mapToolbarEnabled = !mapUiSettings.mapToolbarEnabled
                                    )
                                }) {
                                    Text(text = "Toggle mapToolbarEnabled")
                                }
                            }
                        }

// ...or initialize the map by providing a googleMapOptionsFactory
// This should only be used for values that do not recompose the map such as
// map ID.
                        GoogleMap(
                            googleMapOptionsFactory = {
                                GoogleMapOptions().mapId("MyMapId")
                            }
                        )
                    }
                }
            }
        }
    }
}
package tj.ojsk.egov.core.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun RemoteImage(
    url: String,
    modifier: Modifier = Modifier
) {
    KamelImage(
        resource = asyncPainterResource(url),
        contentDescription = null,
        modifier = modifier.size(48.dp),
        onLoading = {
            Box(modifier = modifier, contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        },
        onFailure = {

        }
    )
}
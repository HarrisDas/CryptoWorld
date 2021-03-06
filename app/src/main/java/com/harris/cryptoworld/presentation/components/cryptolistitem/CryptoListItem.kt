package com.harris.cryptoworld.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.presentation.ui.theme.MediumGray

@Composable
fun CryptoListItem(
    crypto: Crypto,
    onItemClick: ((Crypto) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick?.invoke(crypto) }
            .border(1.dp, MediumGray)
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(crypto.iconUrl),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 16.dp),
            Arrangement.Center,
        ) {
            Text(
                text = crypto.symbol,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = crypto.name,
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Italic,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End
            )
        }

    }
}
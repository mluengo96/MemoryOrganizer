package com.mluengo.memoryorganizer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Archive
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography
import com.mluengo.memoryorganizer.ui.theme.Shapes
import com.mluengo.memoryorganizer.ui.theme.onSurfaceVariantLight

@Composable
fun ItemCard(

) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        onClick = { /* TODO */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .height(IntrinsicSize.Max),
        ) {
            Column(
                modifier = Modifier.weight(1.75f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier.weight(1f) // Adjust weight for first set of Texts
                ) {
                    Text(text = "Tabler Icons: 5450+ free vector icons for web design", style = MemoryOrganizerTypography.titleSmall, overflow = TextOverflow.Ellipsis, minLines = 2)
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                    Text(text = "Tabler Icons", style = MemoryOrganizerTypography.labelSmall, color = onSurfaceVariantLight, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)

                }
                SourceCard()
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Image(painter = painterResource(id = R.drawable.placholder), contentDescription = null, modifier = Modifier.clip(Shapes.extraSmall))
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Row {
                    Button(
                        onClick = { /* TODO */ },
                        shape = CircleShape,
                        modifier = Modifier.size(48.dp),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Archive,
                            contentDescription = "Archive item",
                            modifier = Modifier
                                .size(24.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                    Button(
                        onClick = { /* TODO */ },
                        shape = CircleShape,
                        modifier = Modifier.size(48.dp),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "Delete item",
                            modifier = Modifier
                                .size(24.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false, device = "id:pixel_7a")
@Composable
fun ItemCardPreview() {
    ItemCard()
}
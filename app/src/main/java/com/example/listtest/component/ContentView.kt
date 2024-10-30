package com.example.listtest.component

import android.content.Context
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.listtest.R
import java.util.Locale

@Composable
fun ContentView(modifier: Modifier = Modifier) {
    var isEnglish by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current

    CompositionLocalProvider(
        value = if (isEnglish)
            LocalLayoutDirection provides LayoutDirection.Ltr
        else LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.title),
                modifier = modifier,
            )
            Text(
                text = stringResource(R.string.content),
                Modifier.padding(all = 12.dp)
            )
            Divider(modifier = Modifier.padding(top = 12.dp))
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.select_language),
                    modifier = Modifier.padding(all = 8.dp),
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = isEnglish,
                        onClick = {
                            isEnglish = true
                            toggleLanguage(context, isEnglish)
                        }
                    )
                    Text(text = stringResource(id = R.string.English), Modifier.padding(start = 8.dp))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = !isEnglish,
                        onClick = {
                            isEnglish = false
                            toggleLanguage(context, isEnglish)
                        }
                    )
                    Text(text = stringResource(id = R.string.persion), Modifier.padding(start = 8.dp))
                }
            }
        }
    }

}

private fun toggleLanguage(context: Context, isEnglish: Boolean) {
    val locale = if (isEnglish) Locale("en") else Locale("fa")
    setAppLocale(context, locale)
}

private fun setAppLocale(context: Context, locale: Locale) {
    Locale.setDefault(locale)
    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
    if (context is ComponentActivity) {
        context.recreate()
    }
}
package com.example.passwordmanager.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import coil.compose.rememberImagePainter
import com.example.passwordmanager.R
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.viewmodel.PrimaryReadsViewModel
import dagger.hilt.android.AndroidEntryPoint

class HomePage(private val viewModel: PrimaryReadsViewModel) {

    private var passwords: List<Password> = mutableListOf(
        Password(0,"Some webpage 1", "dumb.com", "dfsdfnjd"),
        Password(1,"Some webpage 2", "dumb.com", "dfsdfnjd"),
        Password(2,"Some webpage 3", "dumb.com", "dfsdfnjd"),
        Password(3,"Some webpage 4", "dumb.com", "dfsdfnjd")
    )

    private fun listenForPasswords(){

    }

    @Composable
    fun GetPasswordList(){

        listenForPasswords()
        LazyColumn {
            items(passwords) { password ->
                PasswordAdapter(password)
            }
        }
    }

    @Composable
    private fun PasswordAdapter(password: Password){

        val context = LocalContext.current

        val heading = if(password.websiteName==null) password.domainName else password.websiteName

        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp,
            modifier = Modifier.fillMaxWidth().padding(0.dp,16.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp,0.dp,0.dp,0.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = rememberImagePainter(
                        "https://www.kindpng.com/picc/m/200-2005293_lock-password-icon-hd-png-download.png"
                    ),
                    contentDescription = "Password icon",
                    modifier = Modifier.size(24.dp).clip(CircleShape).background(Color.Transparent),
                    alignment = Alignment.Center
                )

                if (heading != null) {
                    Text(text = heading
                    )
                }

                Spacer(modifier = Modifier.fillMaxWidth(0.25F))

                Text(text = "* * * * * *",
                )

                Spacer(modifier = Modifier.fillMaxWidth(0.75F))

                IconButton(
                    onClick = {
                    copyToClipboard(
                        text = password.password,
                        context)
                }) {
                    GetCopyImage()
                }

            }
        }
    }

    @Composable
    private fun GetCopyImage(){
        Image(
            painter = painterResource(
                R.drawable.ic_baseline_content_copy_24
            ),
            contentDescription = "Password icon",
            modifier = Modifier.size(24.dp).background(Color.Transparent)
        )
    }

    private fun copyToClipboard(text: String, context: Context){
        val clipboard = ContextCompat.getSystemService(context, ClipboardManager::class.java)
        clipboard?.setText(AnnotatedString(text))
        Toast.makeText(context,"Password copied to clipboard!", Toast.LENGTH_SHORT).show()
    }

}
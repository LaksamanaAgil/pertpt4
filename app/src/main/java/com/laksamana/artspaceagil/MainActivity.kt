package com.laksamana.artspaceagil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.laksamana.artspaceagil.ui.theme.ArtspaceAgilTheme

//TODO: class MainActivity, yang berisi container surface untuk background putih dari materialrheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtspaceAgilTheme  {
                Surface (modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) { //TODO: Menetapkan artspace yang akan ditunjukkan yang telah diimport ke drawables
    val firstArtwork = R.drawable.mtsummit
    val secondArtwork = R.drawable.misty_waterfall
    val thirdArtwork = R.drawable.meadowlake
    val fourthArtwork = R.drawable.mtsummit

    //TODO: Variable mutable state/ state atau nilai yang dapat dengan mudah diubah, agar konten dapat berubah sesuai halaman artspace
    var title by remember { //TODO: Menetapkan variabel judul, tahun, halaman utama, serta gambar yang ditunjukkan, dengan nilai default yang diassign dan dapat berubah dengan mudah, menggunakan mutable state
        mutableStateOf(R.string.mtsummit)
    }

    var year by remember {
        mutableStateOf(R.string.mtsummit_year)
    }

    var currentArtwork by remember {
        mutableStateOf(firstArtwork)
    }

    var imageResource by remember {
        mutableStateOf(currentArtwork)
    }


    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(currentArtwork = currentArtwork)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkTitle(title = title, year = year)
        Spacer(modifier = modifier.size(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            // Previous Button
            Button ( //TODO: Pengaturan tombol kembali
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> { //TODO: jika halaman sekarang merupakan artspace ke-1, maka tombol kembali akan mengarahkan ke halaman artspace ke-4/terakhir
                            currentArtwork = fourthArtwork
                            title = R.string.foresthill
                            year = R.string.foresthill_year
                        }
                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.mtsummit
                            year = R.string.mtsummit_year
                        }
                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.mistywaterfall
                            year = R.string.mistywaterfall_year
                        }
                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.meadowlake
                            year = R.string.meadowlake_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors( //TODO: Warna tombol
                    containerColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.buttonElevation( //TODO: Memberi efek visual tombol ditekan
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text( //TODO: Pengaturan teks dalam tombol kembali
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.blue_300)
                )
            }

            // Next Button
            Button( //TODO: Pengaturan navigasi tombol next/selanjutnya
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.mistywaterfall
                            year = R.string.mistywaterfall_year
                        }
                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.meadowlake
                            year = R.string.meadowlake_year
                        }
                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.foresthill
                            year = R.string.foresthill_year
                        }
                        else -> {
                            currentArtwork = firstArtwork
                            title = R.string.mtsummit
                            year = R.string.mtsummit_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors( //TODO: Pengaturan warna tombol selanjutnya
                    containerColor = colorResource(id = R.color.blue_300)
                ),
                elevation = ButtonDefaults.buttonElevation( //TODO: Efek visual tombol ditekan
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text( //TODO: Pengaturan teks untuk tombol selanjutnya
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.gray_900)
                )
            }
        }
    }
}

@Composable
fun ArtworkDisplay( //TODO: Function untuk menunjukkan gambar artspace beserta judulnya
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Image(
        painter = painterResource(currentArtwork),
        contentDescription = stringResource(id = R.string.mistywaterfall),
        modifier = modifier.fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun ArtworkTitle( //TODO: Function sebagai pengaturan teks judul dan tahun teks
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column ( //TODO: Kolom untuk penempatan teks, dengan teks tersebut ditempatkan ditengah
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( //TODO: Pengaturan teks judul, serperti resource string yang dipanggil, warna teks, ukuran font, dan penetapan teks bold
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue_100),
            fontSize = 32.sp
        )

        Text( //TODO: Pengaturan teks tahun artspace
            text = "— ${stringResource(id = year)} —",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_300)
        )
    }
}
//TODO: Untuk Preview Android Studio
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtspaceAgilTheme  {
        ArtSpaceScreen()
    }
}
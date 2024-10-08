package domain.usecases
import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class ConvertFileToUriUseCase @Inject constructor(
    private val context: Context
) {

    fun execute(uri:Uri):File{
        val tempFile = File(context.cacheDir, "tempFile_${System.currentTimeMillis()}.jpg")
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(tempFile)

            inputStream?.copyTo(outputStream)

            inputStream?.close()
            outputStream.close()

            return tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            return File("")
        }
    }
}
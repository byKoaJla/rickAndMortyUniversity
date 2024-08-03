package by.koajla.characters.mapper

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import by.koajla.characters.Page
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object PageInfoSerializer : Serializer<Page> {
    override val defaultValue: Page
        get() = Page.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Page {
        try {
            return  Page.parseFrom(input)
        } catch (ex : InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto. ", ex)
        }
    }

    override suspend fun writeTo(t: Page, output: OutputStream) = t.writeTo(output)

    val Context.pageStore: DataStore<Page> by dataStore(
        fileName = "page.proto",
        serializer = PageInfoSerializer
    )
}

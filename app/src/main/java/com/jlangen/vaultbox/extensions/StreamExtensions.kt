package com.jlangen.vaultbox.extensions

import java.io.InputStream
import java.io.OutputStream

fun InputStream.copyToStream(outputStream: OutputStream) {
    val buffer = ByteArray(1024)
    var read: Int

    do {
        read = this.read(buffer)
        outputStream.write(buffer, 0, read)
    } while (read != -1)
}